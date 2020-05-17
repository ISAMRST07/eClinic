<template>
	<div>
		<v-card>
			<v-card-title>
				Vacation requests
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
			:items="userVacationRequest"
			:search="search"   
			class="elevation-1"
			:loading="loading"
			loading-text="Loading vacation requests..."
		>
			<template v-slot:item.update="{ item }">
				<v-icon @click="updateDialog(item)" color="amber darken-2">
					mdi-pencil
				</v-icon>
			</template>
			<template v-slot:item.remove="{ item }">
				<v-icon @click="deleteDialog(item)" color="red">
					mdi-delete
				</v-icon>
			</template>

			<template v-slot:no-data>
				<p>There are no vacation requests</p>
			</template>
		</v-data-table>
		<delete-dialog
			v-model="dialog"
			:vacationRequest="vacationRequestToDelete"
			@close="deleteDialog(null)"
			@delete="deleteVacationRequest"
		/>
		<modify-vacation-request-dialog
			mode="update"
			:edit-vacation-request="editVacationRequest"
			v-model="editDialog"
		/>
	</div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import DeleteDialog from "./DeleteDialog";
import ModifyVacationRequestDialog from "./ModifyVacationRequestDialog";

export default {
    name: "VacationRequestTable",
    components: {DeleteDialog, ModifyVacationRequestDialog},
    data: () => ({
    	search : "",
        loading: false,
        descriptionDialog: false,
        editDialog: false,
        dialog: false,
        vacationRequestToDelete: null,
        editVacationRequest: null,
        headers: [
            {text: 'Start date', align: 'start', value: 'startDate'},
            {text: 'End date', align: 'center', value: 'endDate'},
            {text: 'Approved', align: 'center', value: 'approved'},
            {text: 'Update', value: 'update', sortable: false, align: 'center'},
            {text: 'Remove', sortable: false, value: 'remove'},
        ],
    }),
    computed: {
        ...mapState('auth', ['user']),
        ...mapState('auth', ['clinic']),
        ...mapState('clinics/vacationRequest', ['userVacationRequest']),
    },
    methods: {
        ...mapActions('clinics/vacationRequest', ['getUserVacationRequestApi']),
        ...mapActions('clinics/vacationRequest', ['deleteVacationRequestApi']),

        deleteDialog(vacationRequestToDelete) {
            this.vacationRequestToDelete = vacationRequestToDelete;
            this.dialog = !this.dialog;
        },
        deleteVacationRequest() {
            this.deleteVacationRequestApi(this.vacationRequestToDelete);
            this.deleteDialog(null);
        },
        updateDialog(vacationRequest) {
            console.log("updateDialog id = " + vacationRequest.id);
            console.log(vacationRequest);
            this.editVacationRequest = {
                id : vacationRequest.id,
                startDate : vacationRequest.startDate,
                endDate : vacationRequest.endDate,
                approved : vacationRequest.approved,
                user : vacationRequest.user,
                clinic : vacationRequest.clinic
            };
            this.editDialog = true;
        }
    },
    created() {
        this.loading = true;
        console.log(this.user)
        this.getUserVacationRequestApi(this.user.id);
        /*switch (this.user.type) {
            case ClinicalCenterAdmin.code:
                console.log("user = ClinicalCenterAdmin");
                break;
            case ClinicalAdmin.code:
                console.log("user = ClinicalAdmin id = " + this.clinic.id);
                console.log("callapi");
                this.getClinicInterventionApi(this.clinic.id);
                break;
            default:
        }*/
        
    },
    watch: {
        vacationRequest() {
            this.loading = false;
        }
    }
}
</script>

<style scoped></style>