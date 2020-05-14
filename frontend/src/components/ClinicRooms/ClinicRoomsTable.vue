<template>
    <div>
    	<v-card>
			<v-card-title>
				Clinic rooms
				<v-spacer></v-spacer>
				<v-text-field
					v-model="search"
					append-icon="mdi-magnify"
					label="Search"
					single-line
					hide-details
				></v-text-field>
			</v-card-title>
		</v-card>
		
        <v-data-table
                :headers="headers"
                :items="clinicRooms"
           		:search="search"                     
                class="elevation-1"
                :loading="loading"
                loading-text="Visiting all the clinic rooms..."
        >
            <template v-slot:item.update="{ item }">
                <v-icon
                        @click="updateDialog(item)"
                        color="amber darken-2"
                >
                    mdi-pencil
                </v-icon>
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
        <modify-clinic-room-dialog
                mode="update"
                :edit-room="editRoom"
                v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";
    import ModifyClinicRoomDialog from "./ModifyClinicRoomDialog";
    import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "ClinicRoomsTable",
        components: {ModifyClinicRoomDialog, DeleteDialog},
        data: () => ({
        	search : "",
            loading: false,
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            roomToDelete: null,
            editRoom: null,
            headers: [
                {text: 'ID', align: 'start', value: 'id'},
                {text: 'Name', value: 'name', align: 'center'},
                {text: 'Clinic ID', value: 'clinicId'},
                {text: 'Update', value: 'update', sortable: false},
                {text: 'Remove', value: 'remove', sortable: false},

            ],
        }),
        computed: {
            ...mapState('clinicRooms/clinicRooms', ['clinicRooms']),
            ...mapState('auth', ['user']),
        },
        watch: {
            clinicRooms() {
                this.loading = false;
            }
        },
        methods: {
            ...mapActions('clinics/readClinics', ['getClinics']),
            ...mapActions('clinicRooms/clinicRooms', ['getClinicRooms']),
            ...mapActions('clinicRooms/clinicRooms', ['deleteRoomApi']),
            ...mapActions('clinicRooms/clinicRooms', ['getOneClinicRooms']),

            deleteDialog(room) {
                this.roomToDelete = room;
                this.dialog = !this.dialog;
            },
            deleteRoom() {
                this.deleteRoomApi(this.roomToDelete);
                this.deleteDialog(null);
            },
            updateDialog(room) {
                this.editRoom = room;
                this.editDialog = true;
            }

        },
        created() {
            this.loading = true;
            switch (this.user.type) {
                case ClinicalCenterAdmin.code:
                    this.getClinics();
                    this.getClinicRooms();
                    break;
                case ClinicalAdmin.code:
                    console.log(this.user)
                    this.getClinics();
                    this.getOneClinicRooms(this.user);
                    break;
                default:

            }


        }
    }
</script>

<style scoped>

</style>
