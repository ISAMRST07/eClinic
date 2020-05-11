<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            persistent
            max-width="600px"
    >
        <v-card>
            <v-card-title>
                <span v-if="mode === 'add'" class="headline">Add a doctor</span>
                <span v-else class="headline">Update doctor</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-form ref="form">
                        <v-row>
                            <v-col cols="6">
                                <v-text-field
                                        label="Email*"
                                        required
                                        :rules="emailRules"
                                        v-model="email"
                                >
                                </v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                        label="Name*"
                                        required
                                        :rules="nameRules"
                                        v-model="name"
                                >
                                </v-text-field>
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
                                        label="Phone number"
                                        required
                                        :rules="phoneRules"
                                        v-model="phone"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                        label="Address"
                                        required
                                        :rules="addressRules"
                                        v-model="address"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                        label="City"
                                        required
                                        :rules="cityRules"
                                        v-model="city"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                        label="Country"
                                        required
                                        :rules="countryRules"
                                        v-model="country"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                        label="JMBG"
                                        required
                                        :rules="jmbgRules"
                                        v-model="jmbg"
                                ></v-text-field>
                            </v-col>
                            <v-col cols="6">
                                <v-text-field
                                        label="Position*"
                                        required
                                        :rules="positionRules"
                                        v-model="position"
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
                <v-btn
                        color="blue darken-1"
                        v-if="mode === `add`"
                        text
                        @click="submit(addDoctorApi)"
                >Add
                </v-btn
                >
                <v-btn
                        color="blue darken-1"
                        v-else
                        text
                        @click="submit(updateDoctorApi)"
                >Update
                </v-btn
                >

            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>

    import {mapActions, mapState} from "vuex";
    import {emptyDoctor} from "../../utils/skeletons";

    export default {
        name: "ModifyDoctorDialog",
        components: {},
        data: () => ({
            email: null,
            name: null,
            surname: null,
            phone: null,
            address: null,
            city: null,
            country: null,
            jmbg: null,
            position: null,

            doctor: emptyDoctor,

            showPassword: false,

            emailRules: [v => !!v || "Email is required."],
            nameRules: [v => !!v || "Name is required."],
            surnameRules: [v => !!v || "Surname is required"],
            phoneRules: [v => !!v || "Phone is required."],
            addressRules: [v => !!v || "Address is required."],
            cityRules: [v => !!v || "City is required"],
            countryRules: [v => !!v || "Country is required"],
            jmbgRules: [v => !!v || "JMBG is required"],
            positionRules: [v => !!v || "Position is required"]

        }),
        props: {
            editDoctor: null,
            value: false,
            mode: {
                type: String,
                default: "add"
            }
        },
        computed: {
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
        },
        watch: {
            value() {
                if (this.editDoctor) {
                    this.doctor = this.editDoctor;
                    console.log("value changed");
                    console.log("doctorename = " + this.doctor.name);
                }
                this.email = this.doctor.email;
                this.name = this.doctor.name;
                this.surname = this.doctor.surname;
                this.phone = this.doctor.phone;
                this.address = this.doctor.address;
                this.city = this.doctor.city;
                this.country = this.doctor.country;
                this.jmbg = this.doctor.jmbg;
                this.position = this.doctor.position;
                console.log("thisname = " + this.name);
            }
        },
        created() {
            ;
        },
        methods: {
            ...mapActions("doctor/doctor", ["addDoctorApi"]),
            ...mapActions("doctor/doctor", ["updateDoctorApi"]),
            submit(fun) {
                console.log("adddoctor or updatedoctor pressed");
                console.log(this.clinic.id);

                this.doctor.clinic = this.clinic.id;
                if (this.$refs.form.validate()) {
                    this.doctor.clinic = this.clinic.id;
                    this.doctor.email = this.email;
                    this.doctor.name = this.name;
                    this.doctor.surname = this.surname;
                    this.doctor.phone = this.phone;
                    this.doctor.address = this.address;
                    this.doctor.city = this.city;
                    this.doctor.country = this.country;
                    this.doctor.jmbg = this.jmbg;
                    this.doctor.position = this.position;
                    console.log("DOKTOREEE");
                    console.log(this.doctor);
                    fun(this.doctor);
                    this.close();
                }
            },
            close() {
                console.log("close");
                this.$emit('input', false);
                if (this.mode === 'add') this.$refs.form.reset();
            },
            resetLayout() {
                console.log("resetlayout");
                this.doctor = emptyDoctor;
                this.$refs.form.reset();
            }
        }
    };
</script>

<style scoped></style>
