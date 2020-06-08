<template>
    <div>
        <v-container v-if="allowed">
            <v-row>
                <v-col cols="12" sm="4" md="3">
                    <v-card outlined>
                        <v-card-title>
                            Info
                            <v-spacer></v-spacer>
                            <v-btn v-if="editable" text small color="red">Edit</v-btn>
                        </v-card-title>
                        <v-divider></v-divider>
                        <v-list>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        {{medicalRecord.height}} cm
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        Height
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                            <v-divider></v-divider>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        {{medicalRecord.weight}} kg
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        Weight
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                            <v-divider></v-divider>

                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        {{medicalRecord.bloodType}}
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        Blood type
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                            <v-divider></v-divider>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        <v-chip v-for="allergy in medicalRecord.allergies" :key="allergy">
                                            {{allergy}}
                                        </v-chip>
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        Allergies
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </v-card>
                </v-col>
                <v-col cols="12"  sm="8" md="9">
                    <v-card height="21.5em" outlined>
                        <v-card-title>
                            Diagnoses and medicines
                        </v-card-title>
                        <v-divider></v-divider>
                        <v-list>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title class="mb-2">
                                        <v-chip v-for="diagnosis in medicalRecord.diagnoses" :key="diagnosis.id">
                                            {{diagnosis.name}}
                                        </v-chip>
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        Diagnoses
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                            <v-divider></v-divider>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title class="mb-2">
                                        <v-chip v-for="medicine in medicalRecord.medicines" :key="medicine.id">
                                            {{medicine.name}}
                                        </v-chip>
                                    </v-list-item-title>

                                    <v-list-item-subtitle>
                                        Medicines
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                        <v-divider></v-divider>
                    </v-card>
                </v-col>
                <v-col cols="12">
                    <v-card outlined>
                        <v-card-title>
                            Visits history
                        </v-card-title>
                        <v-data-table
                            :headers="visitHeaders"
                            :items="medicalRecord.visits"
                        >

                        </v-data-table>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
        <span v-else>You aren't allowed to visit this page.</span>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import {Doctor} from "../../utils/DrawerItems";

    export default {
        name: "MedicalRecordView",
        data: () => ({
            allowed: true,
            editable: false,
            patientID: null,
            visitHeaders: [
                {text: 'Start', align: 'start', value: 'intervention.dateTime.start'},
                {text: 'Anamnesis', align: 'start', value: 'anamnesis'},
                {text: 'Usage', align: 'start', value: 'usageMethod'},

            ]
        }),
        computed: {
            ...mapState('medicalRecord', ['medicalRecord']),
            ...mapState('auth', ['user', 'role', 'token'])
        },
        methods: {
            ...mapActions('medicalRecord', ['getMedicalRecordApi'])
        },
        created() {
        },
        async mounted() {
            try {
                this.patientID = this.$route.params.id;

                this.getMedicalRecordApi(this.patientID);
                if (this.role === Doctor.code)
                    this.editable = true;

                if(this.user.id === this.patientID) {
                    this.allowed = true;
                    return;
                }

                let {data: upcoming} = await this.$axios.get(`/api/intervention/upcoming/${this.user.id}/${this.patientID}`,
                    {headers: {"Authorization": 'Bearer ' + this.token} });
                if(upcoming) {
                    this.allowed = true;
                    return;
                }
                let {data: exists} = await this.$axios.get(`/api/intervention/${this.user.id}/${this.patientID}`,
                    {headers: {"Authorization": 'Bearer ' + this.token} });

                if(exists) {
                    this.allowed = true;
                    return;
                }
                this.allowed = false;
            } catch(e) {
                this.allowed = false;
            }
        }
    }
</script>

<style scoped>

</style>
