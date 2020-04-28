<template>
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
            {{ item.description | descriptionShortener }}
        </template>
<!--        <template v-slot:item.actions="{ item }">-->
<!--            <v-icon-->
<!--                    small-->
<!--                    class="mr-2"-->
<!--                    @click="editItem(item)"-->
<!--            >-->
<!--                mdi-pencil-->
<!--            </v-icon>-->
<!--            <v-icon-->
<!--                    small-->
<!--                    @click="deleteItem(item)"-->
<!--            >-->
<!--                mdi-delete-->
<!--            </v-icon>-->
<!--        </template>-->
        <template v-slot:no-data>
            <p>There are no existing clinics</p>
        </template>
    </v-data-table>
</template>

<script>
    import {mapActions, mapState} from "vuex";

    export default {
        name: "ClinicsTable",
        data: () => ({
            headers: [
                {
                    text: 'Name',
                    align: 'start',
                    value: 'name',
                },
                { text: 'Description', value: 'description' },
                { text: 'Address', value: 'address' },
            ],
        }),
        computed: {
            ...mapState('clinics/readClinics', ['clinics']),
        },
        methods: {
            ...mapActions('clinics/readClinics', ['getClinics']),
        },
        created() {
            this.getClinics();
        },
        filters: {
            descriptionShortener(val) {
                return val.slice(0, 10)
            }
        }
    }
</script>

<style scoped>

</style>
