<template>
	<v-container fluid>
    	<vacation-request-table/>
    	<template  v-if="role === doctorCode">
    		<add-vacation-request/>
    	</template>
    </v-container>
</template>

<script>
    import {mapState} from "vuex";
	import VacationRequestTable from "../components/VacationRequest/VacationRequestTable";
    import AddVacationRequest from "../components/VacationRequest/AddVacationRequest";
    import {Doctor} from '../utils/DrawerItems';
    
    export default {
        name: "VacationRequest",
        components: {VacationRequestTable, AddVacationRequest},
        data: () => ({
	        doctorCode : Doctor.code,
	    }),
        computed: {
        	...mapState('auth', ['role']),
        }
    }
    
</script>

<style scoped>

</style>
