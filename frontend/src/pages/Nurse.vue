<template>
    <v-container fluid>
        <add-nurse/>
        <nurse-table/>
    </v-container>
</template>

<script>
    import NurseTable from "../components/Nurse/NurseTable";
    import AddNurse from "../components/Nurse/AddNurse";

    export default {
        name: "Nurse",
        components: {AddNurse, NurseTable}
    }
</script>

<style scoped>

</style>
