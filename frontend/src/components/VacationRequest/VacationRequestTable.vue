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
			:items="vacationRequest"
			:search="search"   
			class="elevation-1"
			:loading="loading"
			loading-text="Loading vacation requests..."
		>
			<template  v-if="role === adminCode" v-slot:item.approve="{ item }">
				<v-icon @click="approveVacation(item)" color="amber darken-2">
					mdi-email-check
				</v-icon>
			</template>
			<template  v-if="role === doctorCode" v-slot:item.update="{ item }">
				<v-icon @click="updateDialog(item)" color="amber darken-2">
					mdi-pencil
				</v-icon>
			</template>
			
			<template  v-if="role === adminCode" v-slot:item.disapprove="{ item }">
				<v-icon @click="disapproveVacation(item)" color="amber darken-2">
					mdi-email-check
				</v-icon>
			</template>
			<template v-if="role === doctorCode" v-slot:item.remove="{ item }">
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
import {ClinicalAdmin, Doctor} from '../../utils/DrawerItems';

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
        adminCode: ClinicalAdmin.code,
        doctorCode : Doctor.code,
    }),
    computed: {
        ...mapState('auth', ['user']),
        ...mapState('auth', ['clinic']),
        ...mapState('clinics/vacationRequest', ['vacationRequest']),
        ...mapState('auth', ['role']),
        headers() {
        	let regularHeaders = [
            	{text: 'Start date', align: 'start', value: 'startDate'},
            	{text: 'End date', align: 'center', value: 'endDate'},
            	{text: 'Approved', align: 'center', value: 'approved'},
	            {text: 'Update', value: 'update', align: 'center', sortable: false},
    	        {text: 'Remove', value: 'remove', align: 'center', sortable: false},
            ];
            let adminHeaders = [
                {text: 'Staff name', align: 'start', value: 'user.name'},
                {text: 'Staff email', align: 'center', value: 'user.email'},                
                {text: 'Start date', align: 'center', value: 'startDate'},
            	{text: 'End date', align: 'center', value: 'endDate'},
            	{text: 'Approved', align: 'center', value: 'approved'},
	            {text: 'Approve', align: 'center', value: 'approve', sortable: false },
    	        {text: 'Disapprove', align: 'center', value: 'disapprove', sortable: false},
            ];   
            if (this.role === this.adminCode) {
                    return adminHeaders;
            }
            return regularHeaders;
        },
    },
    methods: {
        ...mapActions('clinics/vacationRequest', ['getUserVacationRequestApi']),   
        ...mapActions('clinics/vacationRequest', ['getClinicVacationRequestApi']),        
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
                startDate : new Date(vacationRequest.startDate),
                endDate : new Date(vacationRequest.endDate),
                approved : vacationRequest.approved,
                user : vacationRequest.user,
                clinic : vacationRequest.clinic
            };
            this.editDialog = true;
        },
        approveVacation(item){
        	console.log("approveVacation");
        	console.log(item);
        },
        disapproveVacation(item){
        	console.log("disapproveVacation");
        	console.log(item);
        }
    },
    created() {
        this.loading = true;
        switch (this.user.type) {
            case Doctor.code:
                console.log("user = Doctor user.id = " + this.user.id);
				this.getUserVacationRequestApi(this.user.id);
                break;
            case ClinicalAdmin.code:
                console.log("user = ClinicalAdmin id = " + this.clinic.id);
                this.getClinicVacationRequestApi(this.clinic.id);
                break;
            default:
        }
        
    },
    watch: {
        vacationRequest() {
            this.loading = false;
        }
    }
}
</script>

<style scoped></style>