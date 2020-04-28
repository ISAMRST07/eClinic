<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="clinics"
                sort-by="calories"
                class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Clinics</v-toolbar-title>
                    <v-divider
                            class="mx-4"
                            inset
                            vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                </v-toolbar>
            </template>
            <template v-slot:item.description="{ item }">
                <v-icon @click="toggleDescriptionDialog(item)">mdi-information</v-icon>
            </template>
            <template v-slot:item.remove="{ item }">
                <v-icon
                        @click="deleteDialog(item)"
                >
                    mdi-delete
                </v-icon>
            </template>
            <template v-slot:no-data>
                <p>There are no existing clinics</p>
            </template>
        </v-data-table>
        <v-dialog
                v-model="dialog"
                max-width="290"
        >
            <v-card>
                <v-card-title class="headline">Deletion</v-card-title>

                <v-card-text>
                    Are you sure that you want to delete <span class="text--primary">{{ deleteName }}</span>?
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                            color="green darken-1"
                            text
                            @click="deleteDialog(null)"
                    >
                        No
                    </v-btn>

                    <v-btn
                            color="green darken-1"
                            text
                            @click="deleteClinic"
                    >
                        Yes
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-dialog
                v-model="descriptionDialog"
                max-width="600"
        >
            <v-card>
                <v-card-title class="headline">{{ descriptionName }}</v-card-title>

                <v-card-text>
                    {{ description }}
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                            color="green darken-1"
                            text
                            @click="toggleDescriptionDialog(null)"
                    >
                        Close
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";

    export default {
        name: "ClinicsTable",
        data: () => ({
            descriptionDialog: false,
            dialog: false,
            clinicToDelete: null,
            clinicWithDescription: null,
            headers: [
                {
                    text: 'Name',
                    align: 'start',
                    value: 'name',
                },
                { text: 'Description', value: 'description', sortable: false},
                { text: 'Address', value: 'address' },
                { text: 'Remove', value: 'remove' }
            ],
        }),
        computed: {
            ...mapState('clinics/readClinics', ['clinics']),
            deleteName() {
                return this.clinicToDelete ? this.clinicToDelete.name : '';
            },
            descriptionName() {
                return this.clinicWithDescription ? this.clinicWithDescription.name : '';
            },
            description() {
                return this.clinicWithDescription ? this.clinicWithDescription.description : '';
            }
        },
        methods: {
            ...mapActions('clinics/readClinics', ['getClinics']),
            ...mapActions('clinics/readClinics', ['deleteClinicApi']),
            deleteDialog(clinic) {
                this.clinicToDelete = clinic;
                this.dialog = !this.dialog;
            },
            deleteClinic() {
                this.deleteClinicApi(this.clinicToDelete);
                this.deleteDialog(null);
            },
            toggleDescriptionDialog(clinic){
                this.clinicWithDescription = clinic;
                this.descriptionDialog = !this.descriptionDialog;
            }
        },
        created() {
            this.getClinics();
        },
        filters: {
            descriptionShortener(val) {
                return val.slice(0, 10)
            }
        },

    }
</script>

<style scoped>

</style>
