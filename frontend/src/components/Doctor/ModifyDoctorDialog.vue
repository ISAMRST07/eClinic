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
                                                <clinic-selection v-model="selectedClinic"/>
                                            </v-col>
                              
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Position*"
                                                        required
                                                        :rules="nameRules"
                                                        v-model="doctor.position"
                                                >
                                                </v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Username*"
                                                        required
                                                        :rules="nameRules"
                                                        v-model="doctor.username"
                                                >
                                                </v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Name*"
                                                        required
                                                        :rules="nameRules"
                                                        v-model="doctor.name"
                                                >
                                                </v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Surname*"
                                                        required
                                                        :rules="surnameRules"
                                                        v-model="doctor.surname"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Password*"
                                                        required
                                                        v-model="doctor.password"
                                                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                                                        :rules="passwordRules"
                                                        :type="showPassword ? 'text' : 'password'"
                                                        name="input-10-2"
                                                        hint="At least 8 characters"
                                                        class="input-group--focused"
                                                        @click:append="showPassword = !showPassword"
                                                >
                                                </v-text-field>
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
            showPassword: false,    
            nameRules: [v => !!v || 'Name is required.'],
            surnameRules: [v => !!v || "* Surname is required"],
            usernameRules: [v => !!v || "* Surname is required"],
            passwordRules : [v => !!v || "* Surname is required"],
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
                    console.log("name " + this.doctor.name);
                    console.log("surname " + this.doctor.surname);
                    console.log("username " + this.doctor.username);
                    console.log("password " + this.doctor.password);
                    console.log("position " + this.doctor.position);
                    
                    console.log("clinicId " + this.selectedClinic.id);
                    console.log("clinicName " + this.selectedClinic.name);
                    
                    this.doctor.clinicId = this.selectedClinic;        
                    
                    this.addDoctorApi(this.doctor);
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
