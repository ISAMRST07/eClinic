<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="clinicRooms"
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Clinic rooms</v-toolbar-title>
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
                <p>There are no existing clinic rooms</p>
            </template>
        </v-data-table>
        <delete-dialog
                v-model="dialog"
                :room="roomToDelete"
                @close="deleteDialog(null)"
                @delete="deleteRoom"
        />
<!--        <modify-clinic-dialog-->
<!--                mode="update"-->
<!--                v-model="editDialog"/>-->
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";

    export default {
        name: "ClinicRoomsTable",
        components: {DeleteDialog},
        data: () => ({
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            roomToDelete: null,
            clinicWithDescription: null,
            headers: [
                { text: 'ID', align: 'start', value: 'id' },
                { text: 'Name', value: 'name', align: 'center'},
                { text: 'Clinic ID', value: 'clinicId' },
                { text: 'Remove', value: 'remove', sortable: false },

            ],
        }),
        computed: {
            ...mapState('clinicRooms/clinicRooms', ['clinicRooms']),
            // editClinic: {
            //     get() {
            //         return this.$store.state.clinics.addClinic.clinic;
            //     },
            //     set(val) {
            //         this.$store.commit('clinics/addClinic/updateClinic', val);
            //     }
            // }
        },
        methods: {
            ...mapActions('clinicRooms/clinicRooms', ['getClinicRooms']),
            ...mapActions('clinicRooms/clinicRooms', ['deleteRoomApi']),

            deleteDialog(room) {
                this.roomToDelete = room;
                this.dialog = !this.dialog;
            },
            deleteRoom() {
                this.deleteRoomApi(this.roomToDelete);
                this.deleteDialog(null);
            },
            // toggleClinicDescription(clinic) {
            //     this.clinicWithDescription = clinic;
            //     this.descriptionDialog = !this.descriptionDialog;
            // },
            // updateDialog(clinic) {
            //     this.editClinic = clinic;
            //     this.editDialog = true;
            // }

        },
        created() {
            this.getClinicRooms();
        }
    }
</script>

<style scoped>

</style>
