
<template>
	<div>
		<v-data-table
			:headers="headers"
			:items="intervention"
			class="elevation-1"
			:loading="loading"
			loading-text="Loading interventions..."
		>
			<template v-slot:top>
				<v-toolbar flat color="white">
					<v-toolbar-title>Interventions</v-toolbar-title>
					<v-divider class="mx-4" inset vertical></v-divider>
					<v-spacer></v-spacer>
				</v-toolbar>
			</template>
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
				<p>There are no interventions</p>
			</template>
		</v-data-table>
		<delete-dialog
			v-model="dialog"
			:intervention="interventionToDelete"
			@close="deleteDialog(null)"
			@delete="deleteIntervention"
		/>
		<modify-intervention-dialog
			mode="update"
			:edit-intervention="editIntervention"
			v-model="editDialog"
		/>
	</div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import DeleteDialog from "./DeleteDialog";
import ModifyInterventionDialog from "./ModifyInterventionDialog";
import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";

export default {
    name: "InterventionTable",
    components: {DeleteDialog, ModifyInterventionDialog},
    data: () => ({
        loading: false,
        descriptionDialog: false,
        editDialog: false,
        dialog: false,
        interventionToDelete: null,
        editIntervention: null,
        headers: [
            {text: 'Date and time', align: 'start', value: 'dateTime'},
            {text: 'Doctor name', align: 'center', value: 'doctor.user.name'},
            {text: 'Clinic room', align: 'center', value: 'clinicRoom.name'},
            {text: 'Intervention type', align: 'center', value: 'interventionType.name'},
            {text: 'Intervention price (in $)', align: 'center', value: 'interventionType.price'},
            {text: 'Duration (in minutes)', align: 'center', value: 'duration'},
            {text: 'Price (in $)', align: 'center', value: 'price'},
            {text: 'Update', value: 'update', sortable: false, align: 'center'},
            {text: 'Remove', sortable: false, value: 'remove'},
        ],
    }),
    computed: {
        ...mapState('intervention/intervention', ['intervention']),
        ...mapState('auth', ['user']),
        ...mapState('auth', ['clinic']),
    },
    methods: {
        ...mapActions('intervention/intervention', ['getClinicInterventionApi']),
        ...mapActions('intervention/intervention', ['getAllInterventionApi']),
        ...mapActions('intervention/intervention', ['deleteInterventionApi']),

        deleteDialog(interventionToDelete) {
            this.interventionToDelete = interventionToDelete;
            this.dialog = !this.dialog;
        },
        deleteIntervention() {
            this.deleteInterventionApi(this.interventionToDelete);
            this.deleteDialog(null);
        },
        updateDialog(intervention) {
            console.log("updateDialog id = " + intervention.id);
            console.log(intervention);
            this.editIntervention = {
                id : intervention.id,
                dateTime : intervention.dateTime,
                selectedClinicRoom : intervention.clinicRoom,
                selectedDoctor : intervention.doctor,
                selectedInterventionType : intervention.interventionType,
                duration : intervention.duration,
                price : intervention.price,
            };
            this.editDialog = true;
        }
    },
    created() {
        this.loading = true;
        console.log(this.user)
        switch (this.user.type) {
            case ClinicalCenterAdmin.code:
                console.log("user = ClinicalCenterAdmin");
                break;
            case ClinicalAdmin.code:
                console.log("user = ClinicalAdmin id = " + this.clinic.id);
                //this.getClinicInterventionApi(this.clinic.id);
                console.log("callapi");
                this.getAllInterventionApi();
                break;
            default:
        }
    },
    watch: {
        intervention() {
            this.loading = false;
        }
    }
}
</script>

<style scoped></style>