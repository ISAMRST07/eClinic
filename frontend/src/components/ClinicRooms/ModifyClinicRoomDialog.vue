<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span v-if="mode === 'add'" class="headline">Add a clinic room</span>
                <span v-else class="headline">Update clinic room</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-form ref="form">
                        <v-row>
                            <v-col cols="12">
                                <v-text-field
                                        label="Name*"
                                        prepend-icon="mdi-form-textbox"
                                        v-model="name"
                                        :rules="nameRules"
                                        required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <clinic-selection v-model="selectedClinic"/>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Close</v-btn>
                <v-btn color="blue darken-1" v-if="mode === `add`" text @click="addClinicRoom">Add</v-btn>
                <v-btn color="blue darken-1" v-else text @click="dialog = false">Update</v-btn>

            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import ClinicSelection from "../Clinics/ClinicSelection";
    import {mapActions} from "vuex";
    import {emptyClinicRoom} from "../../utils/skeletons";
    export default {
        name: "ModifyClinicRoomDialog",
        components: {ClinicSelection},
        data: () => ({
            name: null,
            clinicRoom: emptyClinicRoom,
            selectedClinic: null,
            nameRules: [v => !!v || 'Name is required.']
        }),
        props: {
            clinic: null,
            value: false,
            mode: {
                type: String,
                default: 'add'
            }
        },
        methods: {
            ...mapActions('clinicRooms/clinicRooms', ['addClinicRoomApi']),
            addClinicRoom() {
                if(this.$refs.form.validate()) {
                    this.clinicRoom.name = this.name;
                    this.clinicRoom.clinicId = this.selectedClinic.id;
                    this.addClinicRoomApi(this.clinicRoom);
                    this.close();
                }
            },
            close() {
                this.$emit('input', false);
                this.$refs.form.reset();
            }
        }
    }
</script>

<style scoped>

</style>
