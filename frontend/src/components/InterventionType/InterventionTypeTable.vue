<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="interventionType"
                class="elevation-1"
                :loading="loading"
                loading-text="Getting intervention types"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>intervention types</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>
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
                <p>There are no intervention types</p>
            </template>
        </v-data-table>
        <delete-dialog
                v-model="dialog"
                :interventionType="interventionTypeToDelete"
                @close="deleteDialog(null)"
                @delete="deleteInterventionType"
        />
        <modify-intervention-type-dialog
                mode="update"
                :edit-intervention-type="editInterventionType"
                v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";
    import ModifyInterventionTypeDialog from "./ModifyInterventionTypeDialog";
    import {ClinicalAdmin, ClinicalCenterAdmin} from "../../utils/DrawerItems";

    export default {
        name: "InterventionTypeTable",
        components: {DeleteDialog, ModifyInterventionTypeDialog},
        data: () => ({
            loading: false,
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            interventionTypeToDelete: null,
            editInterventionType: null,
            headers: [
                { text: 'Name', align: 'start', value: 'name' },
                { text: 'Price', align: 'center', value: 'price' },
                { text: 'Update', value: 'update', sortable: false, align: 'center' },
                { text: 'Remove', sortable: false, value: 'remove' },
            ],
        }),
        computed: {
            ...mapState('interventionType/interventionType', ['interventionType']),
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),     
        },
        methods: {
            ...mapActions('interventionType/interventionType', ['getAllInterventionTypeApi']),
            ...mapActions('interventionType/interventionType', ['getClinicInterventionTypeApi']),      
            ...mapActions('interventionType/interventionType', ['deleteInterventionTypeApi']),

            deleteDialog(interventionTypeToDelete) {
                this.interventionTypeToDelete = interventionTypeToDelete;
                this.dialog = !this.dialog;
            },
            deleteInterventionType() {
                this.deleteInterventionTypeApi(this.interventionTypeToDelete);
                this.deleteDialog(null);
            },
            updateDialog(interventionType) {
            	console.log("updateDialog id = " + interventionType.id);
                this.editInterventionType = {
                	id : interventionType.id,
					name : interventionType.name,
					price : interventionType.price,
                };
                this.editDialog = true;
            }
        },
        created() {
        	this.loading = true;
            console.log(this.user)
        	switch (this.user.type) {
                case ClinicalCenterAdmin.code:
                	console.log("user = ClinicalCenterAdmin")
                	this.getAllInterventionTypeApi();
                    break;
                case ClinicalAdmin.code:
                   	console.log("user = ClinicalAdmin id = " + this.clinic.id);   
                   	this.getClinicInterventionTypeApi(this.clinic.id); 
                    break;
                default:
            }
        },
        watch: {
            interventionType() {
                this.loading = false;
            }
        }
    }
</script>

<style scoped>

</style>
