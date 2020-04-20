<template>
    <v-container fluid>
        <v-dialog v-model="addDialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-card>
                <v-toolbar dark color="accent">
                    <v-btn icon dark @click="addDialog = false">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>Add a clinic</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn dark text @click="addDialog = false">Add</v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <v-container>
                    <v-row>
                        <v-col cols="12" md="6">

                        </v-col>
                        <v-col cols="6">
                            <map-view v-model="clinic.coordinates"
                                      class="d-none d-md-flex"
                                      clickable
                                      use-address
                                      :address="mapAddress"
                                      @clickAddress="clinic.address = $event"
                            />
                        </v-col>
                    </v-row>
                    <small>*indicates required field</small>
                </v-container>
                </v-card>
        </v-dialog>
        <v-btn
                fab
                dark
                large
                color="red"
                class="fab-br"
                @click.stop="addDialog = true"
        >
            <v-icon> mdi-plus </v-icon>
        </v-btn>
    </v-container>
</template>

<script>
    import MapView from "../components/MapView";
    export default {
        name: "Clinics",
        components: {MapView},
        data: () => ({
            addDialog: false,
            rules: [v=> !!v || "* Required.", v => v?.length <= 255 || 'Max 256 characters.'],
            clinic: {
                name: String,
                address: null,
                description: String,
                coordinates: null,
            },
            mapAddress: '',
            addressRules: [value => !!value || 'Valid address required.']
        }),
        methods: {
            macak(e) {
            }
        },
        filters: {
            formatCoords(value) {
                if (!value) return '';
                return `(${value.lat.toFixed(6)}, ${value.lng.toFixed(6)})`;
            }
        }
    }
</script>

<style scoped>
    .fab-br {
        position: absolute;
        right: 2em;
        bottom: 3em;
    }

</style>
