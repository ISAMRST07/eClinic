<template>
    <v-container fluid>
		<div>
			<v-data-table
	                :headers="headers"
	                :items="userVacationRequest"
	                class="elevation-1"
	                :loading="loading"
	                loading-text="Getting vacation requests types"
	        >
		        <template v-slot:no-data>
		        	<p>There are no vacation requests</p>
		        </template>
	        </v-data-table>
		</div>
		<v-row justify="space-around">
			Start date:
		    <v-date-picker v-model="startDate" color="green lighten-1"></v-date-picker>
		    End date:
		    <v-date-picker v-model="endDate" color="green lighten-1"></v-date-picker>
		 </v-row>
		<v-btn text color="deep-purple accent-4" @click="request()">
			Request
		</v-btn>
		 
    </v-container>
</template>

<script>

	import {mapActions, mapState} from "vuex";
	import {emptyVacationRequest} from "../utils/skeletons";
	
    export default {
        name: "VacationRequest",
        components: {  },
        data: () => ({
        	startDate : '',
        	endDate : '',
        	vacationRequest : emptyVacationRequest,
        	headers: [
                {text: 'Start date', align: 'start', value: 'startDate'},
                {text: 'End date', align: 'center', value: 'endDate'},
                {text: 'Approved', align: 'center', value: 'approved'},
            ],
        }),
        computed: {
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
            ...mapState('clinics/vacationRequest', ['userVacationRequest']),
            
        },
        watch: {
        },
        methods: {
        	...mapActions('clinics/vacationRequest', ['addVacationRequestApi']),
            ...mapActions('clinics/vacationRequest', ['getUserVacationRequestApi']),
        
        	request(){
        		console.log("request");
        		console.log(new Date(this.startDate));
        		console.log(this.endDate);
        		var start = new Date(this.startDate);
        		var end = new Date(this.endDate)
        		if(dates.compare(start,end) == 0){
        			console.log("dates are equal");
        			alert("dates are equal");
        		}else if(dates.compare(start,end) == 1){
        			console.log("start > end");
        			alert("start > end");
        		}else if(dates.compare(start,end) == -1){
        			console.log("start < end");
        			this.vacationRequest.user = this.user;
        			this.vacationRequest.clinic = this.clinic;
        			this.vacationRequest.startDate = start;
        			this.vacationRequest.endDate = end;
        			this.vacationRequest.approved = false;
        			console.log("created vacation request = ");
        			console.log(this.vacationRequest);
        			this.addVacationRequestApi(this.vacationRequest);
        		}
        	}
        },
        created() {
        	console.log("created");
			console.log(this.user);		//doctor id = user.id
			console.log(this.clinic);	//klinika kojoj ovaj doctor pripada
			this.getUserVacationRequestApi(this.user.id);
			
        }
    }
    var dates = {
	    convert:function(d) {
	        return (
	            d.constructor === Date ? d :
	            d.constructor === Array ? new Date(d[0],d[1],d[2]) :
	            d.constructor === Number ? new Date(d) :
	            d.constructor === String ? new Date(d) :
	            typeof d === "object" ? new Date(d.year,d.month,d.date) :
	            NaN
	        );
	    },
	    compare:function(a,b) {
	        return (isFinite(a=this.convert(a).valueOf()) &&isFinite(b=this.convert(b).valueOf()) ? (a>b)-(a<b) : NaN
	        );
	    }
	}
</script>

<style scoped>

</style>
