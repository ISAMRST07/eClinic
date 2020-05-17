<template>
	<v-container fluid>
    	<vacation-request-table/>
        <add-vacation-request/>
    </v-container>
</template>

<script>
	import VacationRequestTable from "../components/VacationRequest/VacationRequestTable";
    import AddVacationRequest from "../components/VacationRequest/AddVacationRequest";
    
    export default {
        name: "VacationRequest",
        components: {VacationRequestTable, AddVacationRequest}
    }
    
</script>

<style scoped>

</style>
