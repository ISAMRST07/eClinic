<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="doctor"
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Doctors</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>

            <template v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                        color="red"
                >
                    mdi-delete
                </v-icon>
            </template>

            <template v-slot:no-data>
                <p>There are no doctors</p>
            </template>
        </v-data-table>
        <delete-dialog
                v-model="dialog"
                :doctor="doctorToDelete"
                @close="deleteDialog(null)"
                @delete="deleteDoctor"
        />
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";

    export default {
        name: "DoctorTable",
        components: {DeleteDialog},
        data: () => ({
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            doctorToDelete: null,
            headers: [
                { text: 'Doctor', align: 'start', value: 'user.username' },
                { text: 'Position', align: 'center', value: 'position' },
                { text: 'Clinic', align: 'center', value: 'clinic.name' },
                { text: 'Remove', sortable: false, value: 'remove' },
            ],
        }),
        computed: {
            ...mapState('doctor/doctor', ['doctor']),
        },
        methods: {
            ...mapActions('doctor/doctor', ['getDoctor']),
            ...mapActions('doctor/doctor', ['deleteDoctorApi']),

            deleteDialog(doctorToDelete) {
                this.doctorToDelete = doctorToDelete;
                this.dialog = !this.dialog;
            },
            deleteDoctor() {
                this.deleteDoctorApi(this.doctorToDelete);
                this.deleteDialog(null);
            }
        },
        created() {
        	console.log("created");
            this.getDoctor();
        }
    }
</script>

<style scoped>

</style>
