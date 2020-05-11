<template>
    <v-container fluid>
        <add-medicine/>
        <medicine-table/>
    </v-container>
</template>

<script>
    import AddMedicine from "../components/Medicine/AddMenicine";
    import MedicineTable from "../components/Medicine/MedicineTable";

    export default {
        name: "Medicine",
        components: {MedicineTable, AddMedicine}
    }
</script>

<style scoped>

</style>
