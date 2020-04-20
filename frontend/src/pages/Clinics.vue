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
                            <add-clinic-form @mapAddress="mapAddress = $event" @mapDialog="mapDialog = !mapDialog" />
                        </v-col>
                        <v-col cols="6">
                            <map-view v-model="coordinates"
                                      map-id="map"
                                      class="d-none d-md-flex"
                                      clickable
                                      use-address
                                      :address="mapAddress"
                                      @clickAddress="address = $event"
                            />
                        </v-col>
                    </v-row>
                    <small>*indicates required field</small>
                </v-container>
                </v-card>
        </v-dialog>
        <v-dialog  v-model="mapDialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-card>
                <v-toolbar class="toolbar" dark elevation="2" color="primary">
                    <v-toolbar-title>Select a location</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon dark @click="mapDialog = false">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-toolbar>
                <v-container fluid class="mobile-map">
                        <map-view v-model="coordinates"
                                  class="d-flex d-md-none"
                                  clickable
                                  use-address
                                  :address="mapAddress"
                                  map-id="smallMap"
                                  @clickAddress="address = $event"
                        />
                </v-container>
                <v-container class="footer">

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
    import AddClinicForm from "../components/AddClinicForm";
    import {mapMutations, mapState} from "vuex";

    export default {
        name: "Clinics",
        components: {AddClinicForm, MapView},
        data: () => ({
            addDialog: false,
            mapDialog: false,
            mapAddress: null,
        }),
        computed: {
            ...mapState('clinics/addClinic', ['clinic']),
            coordinates: {
                get() {
                    return this.clinic.coordinates;
                },
                set(coords) {
                    this.updateClinic({coordinates: coords});
                }
            },
            address: {
                get() {
                    return this.clinic.address;
                },
                set(address) {
                    this.updateClinic({address: address});
                }
            }
        },
        methods: {
            ...mapMutations('clinics/addClinic', ['updateClinic'])
        }
    }
</script>

<style scoped>
    .fab-br {
        position: absolute;
        right: 2em;
        bottom: 3em;
    }
    .toolbar, .toolbar *{
        z-index: 10;
    }
    .mobile-map {
        z-index: 1;
        position: absolute;
        height: auto;
        top: 56px;
        bottom: 56px;
        margin: 0;
        padding: 0;
    }

</style>
