/* this.$route.params.id*/
<template>
    <div>

        <v-col cols="12">
            <v-text-field
                    label="Name*"
                    required
                    :rules="nameRules"
                    v-model="name"
            ></v-text-field>
        </v-col>
        <v-col cols="12">
            <v-textarea
                    counter="256"
                    outlined
                    label="Description"
                    rows="5"
                    no-resize
                    v-model="description"
                    :rules="descriptionRules"
            ></v-textarea>
        </v-col>
        <v-col cols="12">
            <v-btn>Update</v-btn>
        </v-col>

        <v-container grid-list-md>
            <v-layout row wrap>
                <v-card>
                    <v-card-title primary-title class="blue--text">
                        Doctors:
                    </v-card-title>
                    <v-card-text class="body-1">
                        {{ clinic.doctors.length }}
                    </v-card-text>
                    <v-card-actions>
                        <v-btn @click="doctorDetails" text>Details</v-btn>
                    </v-card-actions>
                </v-card>

                <v-card>
                    <v-card-title primary-title class="blue--text">
                        Nurses:
                    </v-card-title>
                    <v-card-text class="body-1">
                        {{ clinic.nurses.length }}
                    </v-card-text>
                    <v-card-actions>
                        <v-btn @click="nurseDetails" text>Details</v-btn>
                    </v-card-actions>
                </v-card>

                <v-card>
                    <v-card-title primary-title class="blue--text">
                        Clinic rooms:
                    </v-card-title>
                    <v-card-text class="body-1">
                        {{ clinic.clinicRoom.length }}
                    </v-card-text>
                    <v-card-actions>
                        <v-btn @click="clinicRoomDetails" text>Details</v-btn>
                    </v-card-actions>
                </v-card>

                <v-card>
                    <v-card-title primary-title class="blue--text">
                        Patients:
                    </v-card-title>
                    <v-card-text class="body-1">
                        {{ clinic.patients.length }}
                    </v-card-text>
                    <v-card-actions>
                        <v-btn @click="patientsDetails" text>Details</v-btn>
                    </v-card-actions>
                </v-card>
            </v-layout>
        </v-container>
    </div>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "ClinicDetailsComponent",
        components: {},
        data: () => ({
            nameRules: [v => !!v || "* Name is required"],
            descriptionRules: [v => !!v || "* Required.", v => v?.length <= 255 || 'Max 256 characters.']
        }),
        computed: {
            ...mapState('auth', ['clinic']),

            name: {
                get() {
                    return this.clinic.name;
                },
                set(val) {
                    this.updateClinic({name: val});
                }
            },
            description: {
                get() {
                    return this.clinic.description;
                },
                set(val) {
                    this.updateClinic({description: val});
                }
            }
        },
        methods: {
            submit(fun) {
                let valid = this.$refs.form.validate();
                if (valid) {
                    fun(this.clinic);
                    this.closeAddDialog();
                }
            },
            doctorDetails() {
                console.log("doctorDetails");
                //this.$router.push('Doctors')
            },
            nurseDetails() {
                console.log("nurseDetails details");
            },
            clinicRoomDetails() {
                console.log("clinicRoomDetails");
            },
            patientsDetails() {
                console.log("patientsDetails");
            },

        },
        created() {
            console.log("created");
            console.log("getting clinic id = " + this.clinic.id);
        }
    };

</script>

<style scoped></style>
