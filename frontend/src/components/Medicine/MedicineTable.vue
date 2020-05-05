<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="medicines"
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Medicines</v-toolbar-title>
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
                <p>There are no existing clinic rooms</p>
            </template>
        </v-data-table>
        <delete-medicine
                v-model="dialog"
                :medicine="medicineToDelete"
                @close="deleteDialog(null)"
                @delete="deleteMedicine"
        />
        <modify-medicine
                    mode="update"
                    :edit-medicine="editMedicine"
                    v-model="editDialog"/>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapState} from "vuex";
    import DeleteMedicine from "./DeleteMedicine";
    import ModifyMedicine from "./ModifyMedicine";

    export default {
        name: "MedicineTable",
        components: {ModifyMedicine, DeleteMedicine},
        data: () => ({
            descriptionDialog: false,
            editDialog: false,
            dialog: false,
            medicineToDelete: null,
            editMedicine: null,
            headers: [
                { text: 'ID', align: 'start', value: 'id' },
                { text: 'Name', value: 'name', align: 'center'},
                { text: 'Manufacturer', value: 'manufacturer' },
                { text: 'Update', value: 'update', sortable: false },
                { text: 'Remove', value: 'remove', sortable: false },

            ],
        }),
        computed: {
            ...mapState('medicines/medicines', ['medicines']),
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
            ...mapActions('clinics/readClinics', ['getClinics']),
            ...mapActions('medicines/medicines', ['getMedicines']),
            ...mapActions('medicines/medicines', ['deleteMedicineApi']),

            deleteDialog(medicine) {
                this.medicineToDelete = medicine;
                this.dialog = !this.dialog;
            },
            deleteMedicine() {
                this.deleteMedicineApi(this.medicineToDelete);
                this.deleteDialog(null);
            },
            updateDialog(room) {
                this.editMedicine = room;
                this.editDialog = true;
            }

        },
        created() {
            this.getClinics();
            this.getMedicines();
        }
    }
</script>

<style scoped>

</style>
