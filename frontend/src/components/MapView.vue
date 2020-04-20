<template>
    <div id="map">

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
        }),
        mounted() {
            delete L.Icon.Default.prototype._getIconUrl;
            L.Icon.Default.mergeOptions({
                iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
                iconUrl: require("leaflet/dist/images/marker-icon.png"),
                shadowUrl: require("leaflet/dist/images/marker-shadow.png")
            });

            this.map = L.map('map').setView([42.35, -71.08], 2);
            this.tileLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map);
            this.map.on('click', this.addMarker);
        },
        methods: {
            addMarker(e) {
                if(this.currentMarker !== null)
                    this.map.removeLayer(this.currentMarker);
                this.currentMarker = L.marker(e.latlng);
                this.currentMarker.addTo(this.map);
            }
        }
    }
</script>

<style scoped>
    #map {
        width: 100%;
        height: 100%;
    }
</style>
