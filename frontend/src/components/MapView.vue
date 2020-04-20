<template>
    <div :id="mapId"
         style="width: 100%; height: 100%;"
    >

    </div>
</template>

<script>
    import 'leaflet/dist/leaflet.css';
    import L from 'leaflet';
    export default {
        name: "MapView",
        data: () => ({
            map: null,
            currentMarker: null,
            isActive: false
        }),
        props: {
            mapId: {
                type: String,
                default: 'map'
            },
            value: null,
            clickable: {
                type: Boolean,
                default: false
            },
            useAddress: {
                type: Boolean,
                default: false
            },
            address: {
                type: String,
                default: null
            }
        },
        watch: {
            address(newAddress, oldAddress) {
                if(newAddress !== oldAddress) {
                    this.coordsFromAddress(newAddress);
                }
            }
        },
        mounted() {
            delete L.Icon.Default.prototype._getIconUrl;
            L.Icon.Default.mergeOptions({
                iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
                iconUrl: require("leaflet/dist/images/marker-icon.png"),
                shadowUrl: require("leaflet/dist/images/marker-shadow.png")
            });

            this.map = L.map(this.mapId).setView([0, 0], 2);
            this.tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map);
            console.log(this.map);
            if (this.clickable) this.map.on('click', this.addMarker);
            if (this.value) this.placeMarker(this.value);
            setTimeout(() => this.map.invalidateSize(), 400);
        },
        updated() {
            this.map.invalidateSize();
        },
        methods: {
            addMarker(e) {
                this.placeMarker(e.latlng);
                this.$emit('input', e.latlng);
            },
            placeMarker(latlng) {
                if(this.currentMarker !== null)
                    this.map.removeLayer(this.currentMarker);
                this.currentMarker = L.marker(latlng);
                this.currentMarker.addTo(this.map);
                if (this.useAddress) this.addressFromCoords(latlng);
            },
            async addressFromCoords(latlng) {
                let res = await this.reverseGeoCode(latlng);
                let address= res.data.display_name;
                if (!address) this.$emit('clickAddress', null);
                this.$emit('clickAddress', res.data.display_name);
            },
            async coordsFromAddress(newAddress) {
                try {
                    let res = await this.geoCode(newAddress);
                    let latlng = {lat: Number(res.data[0].lat), lng: Number(res.data[0].lon)};
                    let address = res.data[0].display_name;
                    this.placeMarker(latlng);
                    this.map.flyTo(new L.LatLng(latlng.lat, latlng.lng), 15);
                    this.$emit('clickAddress', address);
                    this.$emit('input', latlng);
                } catch (err) {
                    this.$emit('clickAddress', null);
                }
            }
            ,
            reverseGeoCode(latlng) {
                let query = `reverse?format=jsonv2&lat=${latlng.lat}&lon=${latlng.lng}`;
                return this.$axios.get(`https://nominatim.openstreetmap.org/${query}`);
            },
            geoCode(address) {
                let query = `?q=${address}&format=jsonv2&addressdetails=1&limit=1`;
                return this.$axios.get(`https://nominatim.openstreetmap.org/${query}`);
            }
        }
    }
</script>

<style scoped>
</style>
