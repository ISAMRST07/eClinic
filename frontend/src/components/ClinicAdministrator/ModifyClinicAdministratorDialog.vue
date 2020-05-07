<template>
    <div>
        <v-dialog
                :value="value"
                @input="$emit('input', false)"
                persistent
                max-width="600px"
        >

            <v-card>
                <v-card-title>
                    <span v-if="mode === 'add'" class="headline">Add a clinic administrator</span>
                    <span v-else class="headline">Update clinic administrator</span>
                </v-card-title>
                <v-card-text>
                    <v-container>

                                <v-form ref="form">
                                    <template>
                                        <v-row>
                                            <v-col cols="12">
                                                <clinic-selection v-model="selectedClinic"/>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Name*"
                                                        required
                                                        :rules="nameRules"
                                                        v-model="name"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Surname*"
                                                        required
                                                        :rules="surnameRules"
                                                        v-model="surname"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        ref="password"
                                                        label="Password*"
                                                        required
                                                        v-model="password"
                                                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                                                        :rules="passwordRules"
                                                        :type="showPassword ? 'text' : 'password'"
                                                        name="input-10-2"
                                                        hint="At least 8 characters"
                                                        class="input-group--focused"
                                                        @click:append="showPassword = !showPassword"
                                                ></v-text-field>
                                            </v-col>

                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Confirm password*"
                                                        required
                                                        v-model="confpass"
                                                        :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
                                                        :rules="passwordRules"
                                                        :type="showPassword2 ? 'text' : 'password'"
                                                        name="input-10-2"
                                                        hint="At least 8 characters"
                                                        class="input-group--focused"
                                                        @click:append="showPassword2 = !showPassword2"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="12">
                                                <v-text-field
                                                        label="Email*"
                                                        required
                                                        :rules="emailRules"
                                                        v-model="email"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="City*"
                                                        required
                                                        :rules="cityRules"
                                                        v-model="city"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="State*"
                                                        required
                                                        :rules="stateRules"
                                                        v-model="country"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Address*"
                                                        required
                                                        :rules="addressRules"
                                                        v-model="address"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Personal ID*"
                                                        required
                                                        :rules="umcnRules"
                                                        v-model="personalID"
                                                ></v-text-field>
                                            </v-col>
                                        </v-row>
                                    </template>
                                </v-form>
                        <small>*indicates required field</small>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeAddDialog">Close</v-btn>
                    <v-btn color="blue darken-1" v-if="mode === `add`" text @click="submit(saveClinicAdmin)">Add</v-btn>
                    <v-btn color="blue darken-1" v-else text @click="submit(modifyClinicApi)">Update</v-btn>

                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import {mapActions, mapMutations} from "vuex";
    import {emptyClinic, emptyClinicAdmin} from "../../utils/skeletons";
    import ClinicSelection from "../Clinics/ClinicSelection";

    export default {
        name: "ModifyClinicAdministratorDialog",
        components: {ClinicSelection},
        data: () => ({
            //*************************************************************************************************************
            selectedClinic: null, // ************************************************************* OVO CES DA METNES ADMINU
            //*************************************************************************************************************
            confpass : '',
            clinicAdmin: emptyClinicAdmin,
            name: '',
            surname: '',
            password: '',
            username: '',
            city : '',
            country : '',
            email : '',
            personalID : '',
            address: '',

            showPassword: false,
            showPassword2: false,
            addressRules : [v => !!v || "* Address is required"],
            emailRules: [v => !!v || "* Email is required",
                v => /.+@.+/.test(v) || "E-mail must be valid"],
            cityRules: [v => !!v || "* City is required"],
            stateRules: [v => !!v || "* Country is required"],
            umcnRules: [v => !!v || "* Personal ID is required"],
            nameRules: [v => !!v || "* Name is required"],
            surnameRules: [v => !!v || "* Sutname is required"],
            usernameRules: [v => !!v || "* Username is required"],
            passwordRules : [v => !!v || "* Password is required"]
        }),
        props: {
            editClinicAdmin : null,
            clinicToUpdate:{
                default: null
            },
            value: null,
            mode: {
                type: String,
                default: 'add'
            }
        },
        watch: {
            value() {
                if(this.editClinicAdmin) {
                    this.name = this.editClinicAdmin.name;
                    this.surname = this.editClinicAdmin.surname;
                    this.password = this.editClinicAdmin.password;
                    this.username = this.editClinicAdmin.username;
                    this.confpass = this.editClinicAdmin.password;
                    this.city = this.editClinicAdmin.city;
                    this.email = this.editClinicAdmin.email;
                    this.country =this.editClinicAdmin.country;
                    this.personalID = this.editClinicAdmin.personalID;
                    this.address = this.editClinicAdmin.address;
                    this.selectedClinic = this.editClinicAdmin.clinic;
                    this.clinicAdmin = this.editClinicAdmin;
                }
                else{
                    this.name = "";
                    this.surname = "";
                    this.password = "";
                    this.username = "";
                    this.city = "";
                    this.country = "";
                    this.personalID = "";
                    this.address = "";
                    this.selectedClinic = null;
                    this.clinicAdmin = emptyClinicAdmin;
                }

            }
        },
        filters: {
            formatCoords(value) {
                if (!value) return '';
                return `(${value.lat.toFixed(6)}, ${value.lng.toFixed(6)})`;
            }
        },
        methods: {
            ...mapActions('clinicAdmins/readClinicAdmins', ['saveClinicAdmin']),
            ...mapActions('clinicAdmins/readClinicAdmins', ['modifyClinicApi']),
            setAddress(event) {
                if(!event) this.address = null;
                else {
                    this.address = event[0];
                }

            },
            closeAddDialog() {
                this.$emit('input', false);
            },
            submit(fun) {
                let valid = this.$refs.form.validate();
                if (valid) {
                    if (this.mode === "update"){
                        this.clinicToUpdate.name = this.name;
                        this.clinicToUpdate.surname = this.surname;
                        this.clinicToUpdate.password = this.password;
                        this.clinicToUpdate.username = this.username;
                        this.clinicToUpdate.city = this.city;
                        this.clinicToUpdate.country =this.country;
                        this.clinicToUpdate.personalID = this.personalID;
                        this.clinicToUpdate.address = this.address;
                        this.clinicToUpdate.email = this.email;
                        this.clinicToUpdate.clinic = this.selectedClinic;
                        console.log(this.selectedClinic.id)
                        console.log(this.selectedClinic)
                        fun(this.clinicToUpdate);
                    }
                    if (this.mode === "add") {
                        this.clinicAdmin.name = this.name;
                        this.clinicAdmin.surname = this.surname;
                        this.clinicAdmin.password = this.password;
                        this.clinicAdmin.username = this.username;
                        this.clinicAdmin.city = this.city;
                        this.clinicAdmin.country =this.country;
                        this.clinicAdmin.personalID = this.personalID;
                        this.clinicAdmin.address = this.address;
                        this.clinicAdmin.email = this.email;
                        this.clinicAdmin.clinic = this.selectedClinic;
                        fun(this.clinicAdmin);
                    }
                    this.closeAddDialog();
                }
            }
        }
    }
</script>

<style scoped>
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
