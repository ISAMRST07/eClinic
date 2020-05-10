<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="nurse"
                class="elevation-1"
                :loading="loading"
                loading-text="Contacting all the nurses to see if they still work here..."
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Nurse</v-toolbar-title>
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
                <p>There are no nurses</p>
            </template>
        </v-data-table>
        <delete-dialog
                v-model="dialog"
                :nurse="nurseToDelete"
                @close="deleteDialog(null)"
                @delete="deleteNurse"
        />
        <modify-nurse-dialog
                mode="update"
                :edit-nurse="editNurse"
                v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import DeleteDialog from "./DeleteDialog";
    import ModifyNurseDialog from "./ModifyNurseDialog";

    export default {
        name: "NurseTable",
        components: {DeleteDialog, ModifyNurseDialog},
        data: () => ({
            loading: false,  
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            nurseToDelete: null,
            editNurse: null,     
            headers: [
                { text: 'Name', align: 'start', value: 'user.name' },
                { text: 'Surname', align: 'center', value: 'user.surname' },
                { text: 'Email', align: 'center', value: 'user.email' },
                { text: 'Phone number', align: 'center', value: 'user.phoneNumber' },
                { text: 'Address', align: 'center', value: 'user.address' },
                { text: 'Position', align: 'center', value: 'position' },
                { text: 'Update', value: 'update', sortable: false, align: 'center' },      
                { text: 'Remove', sortable: false, value: 'remove' },
            ],
        }),
        computed: {
            ...mapState('nurse/nurse', ['nurse']),
        },
        methods: {
            ...mapActions('nurse/nurse', ['getNurse']),
            ...mapActions('nurse/nurse', ['deleteNurseApi']),

            deleteDialog(nurseToDelete) {
                this.nurseToDelete = nurseToDelete;
                this.dialog = !this.dialog;
            },
            deleteNurse() {
                this.deleteNurseApi(this.nurseToDelete);
                this.deleteDialog(null);
            },
            updateDialog(nurse) {
            	console.log("updateDialog id = " + nurse.id);
                this.editNurse = {
                	id : nurse.id,
                	email : nurse.user.email,
					name : nurse.user.name,
					surname : nurse.user.surname,
					phone : nurse.user.phoneNumber,
					address : nurse.user.address,
					city : nurse.user.city,
					country : nurse.user.country,
					jmbg : nurse.user.personalID,
					position: nurse.position
                };
                this.editDialog = true;
            }
        },
        created() {
           	this.loading = true;
        	console.log("created");
        	if(this.$route.params.id == undefined){	
        		//ovde prikazi sve sestre koje postoje
        		console.log("sve sestre");
            	this.getNurse();        	
        	}else{
        		//ovde priakzi sestre sa klinike ciji id = this.$route.params.id
				console.log("samo sa klinike sestre");
        		this.getNurse(this.$route.params.id);     	   	
        	}
        },
        watch: {
            nurse() {
                this.loading = false;
            }
        }
    }
</script>

<style scoped>

</style>
