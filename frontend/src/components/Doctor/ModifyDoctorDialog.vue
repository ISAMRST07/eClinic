<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            persistent max-width="600px">
        <v-card>
            <v-card-title>
                <span v-if="mode === 'add'" class="headline">Add a doctor</span>
                <span v-else class="headline">Update doctor</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-form ref="form">
                        <v-row>
                            <v-col cols="12">
                                <doctor-selection v-model="selectedDoctor"/>
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
                <v-btn color="blue darken-1" v-if="mode === `add`" text @click="addDoctor">Add</v-btn>
                <v-btn color="blue darken-1" v-else text @click="dialog = false">Update</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import ClinicSelection from "../Clinics/ClinicSelection";
    import DoctorSelection from "../Doctor/DoctorSelection"; 
    import {mapActions} from "vuex";
    import {emptyDoctor} from "../../utils/skeletons";
    export default {
        name: "ModifyDoctorDialog",
        components: {ClinicSelection, DoctorSelection},
        data: () => ({
            name: null,
            doctor: emptyDoctor,
            selectedDoctor: null,
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
            ...mapActions('doctor/doctor', ['addDoctorApi']),
            addDoctor() {
                if(this.$refs.form.validate()) {
                    console.log(this.selectedDoctor.user.name);
                    console.log(this.selectedClinic.name);              
                    //this.doctor.name = this.name;
                    //this.doctor.clinicId = this.selectedClinic.id;
                    //this.addDoctorApi(this.doctor);
                    //this.close();
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
