<template>
    <v-container fluid>
    	<intervention-table/>
        <add-intervention/>
    </v-container>
</template>

<script>
    import InterventionTable from "../components/Intervention/InterventionTable";
    import AddIntervention from "../components/Intervention/AddIntervention";
    
    export default {
        name: "Intervention",
        components: {InterventionTable, AddIntervention}
    }
</script>

<style scoped>

</style>

