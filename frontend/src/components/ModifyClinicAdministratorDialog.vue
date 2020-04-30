<template>
    <div>
        <v-dialog
                :value="value"
                @input="$emit('input', false)"
                hide-overlay
                transition="dialog-bottom-transition"
        >
            <v-card>
                <v-toolbar dark class="toolbar" color="accent">
                    <v-btn icon dark @click="closeAddDialog">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>Add a clinic administrator</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn v-if="mode === 'add'" dark text @click="submit(saveClinicAdmin)">Add</v-btn>
                        <v-btn v-else dark text @click="submit(modifyClinicApi)">Update</v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <v-container>

                            <v-form ref="form">
                                <template>
                                    <v-row>
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
                                                    value="wqfasds"
                                                    class="input-group--focused"
                                                    @click:append="showPassword = !showPassword"
                                            ></v-text-field>
                                        </v-col>
                                    </v-row>



                                </template>
                            </v-form>


                    <small>*indicates required field</small>
                </v-container>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import {mapActions, mapMutations} from "vuex";
    import {emptyClinic, emptyClinicAdmin} from "../utils/skeletons";

    export default {
        name: "ModifyClinicAdministratorDialog",
        components: {},
        data: () => ({
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
                    console.log("OVDEEEE")
                    if (this.mode === "update"){
                        console.log("OVDE USAO BRE")
                        console.log(this.clinicToUpdate)
                        this.clinicToUpdate.password = this.clinicAdmin.password;
                        this.clinicToUpdate.username = this.clinicAdmin.username;
                        this.clinicToUpdate.name = this.clinicAdmin.name;
                        this.clinicToUpdate.surname = this.clinicAdmin.surname;
                        fun(this.clinicToUpdate);
                    }
                    else {
                        console.log(this.clinicAdmin);
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
