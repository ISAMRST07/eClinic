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
                                                        v-model="clinicAdmin.name"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Surname*"
                                                        required
                                                        :rules="surnameRules"
                                                        v-model="clinicAdmin.surname"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Username*"
                                                        required
                                                        :rules="usernameRules"
                                                        v-model="clinicAdmin.username"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col cols="6">
                                                <v-text-field
                                                        label="Password*"
                                                        required
                                                        v-model="clinicAdmin.password"
                                                        :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                                                        :rules="passwordRules"
                                                        :type="showPassword ? 'text' : 'password'"
                                                        name="input-10-2"
                                                        hint="At least 8 characters"
                                                        class="input-group--focused"
                                                        @click:append="showPassword = !showPassword"
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
            clinicAdmin: emptyClinicAdmin,
            showPassword: false,
            nameRules: [v => !!v || "* Name is required"],
            surnameRules: [v => !!v || "* Surname is required"],
            usernameRules: [v => !!v || "* Surname is required"],
            passwordRules : [v => !!v || "* Surname is required"]
        }),
        props: {
            clinicToUpdate:{
                default: null
            },
            value: null,
            mode: {
                type: String,
                default: 'add'
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
                        this.clinicToUpdate.password = this.clinicAdmin.password;
                        this.clinicToUpdate.username = this.clinicAdmin.username;
                        this.clinicToUpdate.name = this.clinicAdmin.name;
                        this.clinicToUpdate.surname = this.clinicAdmin.surname;
                        fun(this.clinicToUpdate);
                    }
                    else {
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