<template>
    <div>
        <v-dialog v-model="addDialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-card>
                <v-toolbar dark class="toolbar" color="accent">
                    <v-btn icon dark @click="closeAddDialog">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>Add a clinic</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn dark text @click="addClinic">Add</v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <v-container>
                    <v-row>
                        <v-col cols="12" md="6">
                            <v-form ref="form">
                                <add-clinic-form
                                        @mapAddress="mapAddress = $event"
                                        @mapDialog="mapDialog = !mapDialog"
                                />
                            </v-form>
                        </v-col>
                        <v-col cols="6">
                            <map-view v-model="coordinates"
                                      map-id="map"
                                      class="d-none d-md-flex"
                                      clickable
                                      use-address
                                      :address="mapAddress"
                                      @clickAddress="address = $event[0]"
                            />
                        </v-col>
                    </v-row>
                    <small>*indicates required field</small>
                </v-container>
            </v-card>
        </v-dialog>
        <v-dialog  v-model="mapDialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-card height="100%">
                <v-toolbar class="toolbar" dark elevation="2" color="primary">
                    <v-toolbar-title>Select a location</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon dark @click="mapDialog = false">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-toolbar>
                <v-card-text class="card-body">
                    <map-view v-model="coordinates"
                              class="d-flex d-md-none"
                              clickable
                              use-address
                              :address="mapAddress"
                              map-id="smallMap"
                              @clickAddress="setAddress"
                    />
                </v-card-text>
                <v-fade-transition>
                    <v-card v-show="!!clinic.address && clinic.address.length > 0"
                            tile
                            outlined
                            class="footer"
                    >
                        <v-card-text>
                            <p class="font-weight-medium">{{ clinic.address }}</p>
                        </v-card-text>
                    </v-card>
                </v-fade-transition>
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
    </div>
</template>

<script>
    import AddClinicForm from "./AddClinicForm";
    import MapView from "./MapView";
    import {mapActions, mapMutations} from "vuex";
    import {emptyClinic} from "../utils/skeletons";

    export default {
        name: "AddClinic",
        components: {AddClinicForm, MapView},
        data: () => ({
            addDialog: false,
            mapDialog: false,
            mapAddress: null,
        }),
        computed: {
            clinic: {
                get() {
                    return this.$store.state.clinics.addClinic.clinic;
                },
                set(val) {
                    this.updateClinic(val);
                }
            },
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
            ...mapMutations('clinics/addClinic', ['updateClinic']),
            ...mapActions('clinics/addClinic', ['saveClinic']),
            setAddress(e) {
                // ovo treba da se doradi za autocomplete
                this.address = e[0];
            },
            closeAddDialog() {
                this.addDialog = false;
                this.clinic = emptyClinic;
                this.mapAddress = '';
            },
            addClinic() {
                let valid = this.$refs.form.validate();
                console.log(valid);
                if (valid) {
                    this.saveClinic(this.clinic);
                    this.addDialog = false;
                    this.clinic = emptyClinic;
                    this.mapAddress = '';
                }
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
    .card-body {
        margin: auto 0 auto 0;
        height: 91%;
        width: 100%;
        padding: 0 !important;
    }
    .footer{
        position: absolute;
        bottom: 0;
        height: fit-content;
        width: 100%;
        z-index: 1000;
        background-color: red;
    }
</style>
