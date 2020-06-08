<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="patients"
                :loading="loading"
                loading-text="Gathering all patients..."
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Patients</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>

            <template v-if="role !== adminCode " v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                        color="red"
                >
                    mdi-delete
                </v-icon>
            </template>

            <template v-slot:item.name="{ item }">
                {{ item.user.name }} {{ item.user.surname }}
            </template>

            <template v-slot:item.address="{ item }">
                {{ item.user.city }}, {{ item.user.country }}
            </template>

            <template v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                        color="red"
                >
                    mdi-delete
                </v-icon>
            </template>
            <template v-slot:item.medicalRecord="{ item }">
                <v-icon
                        v-if="!item.medicalRecord"
                        @click="createMedicalRecord(item)"
                        color="amber darken-2"
                >
                    mdi-clipboard-pulse-outline
                </v-icon>

                <v-icon
                        v-else
                        @click=""
                        color="amber darken-2"
                >
                    mdi-clipboard-pulse
                </v-icon>

            </template>
            <template v-slot:no-data>
                <p>This clinical center doesn't have any patients registered.</p>
            </template>
        </v-data-table>
        <delete-dialog
                v-model="dialog"
                :patient="patientToDelete"
                @close="deleteDialog(null)"
                @delete="deleteClinic"
        />
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";
    import {ClinicalCenterAdmin} from '../../utils/DrawerItems';

    export default {
        name: "PatientsTable",
        components: {DeleteDialog},
        data: () => ({
            loading: false,
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            patientToDelete: null,
            adminCode: ClinicalCenterAdmin.code,
            headers: [
                {text: 'Name', value: 'name', align: 'center'},
                {text: 'Email', value: 'user.email', align: 'center'},
                {text: 'Address', value: 'address'},
                {text: 'Medical record', value: 'medicalRecord', sortable: false},
                {text: 'Remove', value: 'remove', sortable: false},
            ]
        }),
        computed: {
            ...mapState('patients', ['patients']),
            ...mapState('auth', ['role', 'clinic']),
        },
        watch: {
            patients() {
                console.log(this.patients)
                this.loading = false;
            }
        },
        methods: {
            ...mapActions('patients', ['getPatients', 'getPatientsByClinic']),
            ...mapActions('patients', ['createRecord']),

            deleteDialog(patient) {
                this.patientToDelete = patient;
                this.dialog = !this.dialog;
            },
            deleteClinic() {
                this.deletePatient(this.clinicToDelete);
                this.deleteDialog(null);
            },
            createMedicalRecord(patient) {
                this.loading = true;
                this.createRecord(patient);
            }

        },
        created() {
            this.loading = true;
            console.log("ULOGA")
            console.log(this.role)
            console.log(this.clinic.id)
            if(this.role=== "doctor" || this.role=== "nurse"){
                console.log("opa micko");
                this.getPatientsByClinic(this.clinic);
            }
            else{
                this.getPatients();
            }
        }

    }
</script>

<style scoped>

</style>
