<template>
    <div>
    	<v-container fluid>
    		<v-card class="mx-auto" max-width="344">
				<v-card-text>
					<p class="display-1 text--primary">
						Patients : {{this.clinic.patients.length}}
					</p>
				</v-card-text>
				<v-card-actions>
					<v-btn text color="deep-purple accent-4">
						Details
					</v-btn>
				</v-card-actions>
			</v-card>
			
			<v-card class="mx-auto" max-width="344">
				<v-card-text>
					<p class="display-1 text--primary">
						Interventions: {{this.clinic.interventions.length}}
					</p>
				</v-card-text>
				<v-card-actions>
					<v-btn text color="deep-purple accent-4">
						Details
					</v-btn>
				</v-card-actions>
			</v-card>
			
			<v-card class="mx-auto" max-width="344">
				<v-card-text>
					<p class="display-1 text--primary">
						Calendar
					</p>
				</v-card-text>
				<v-card-actions>
					<v-btn text color="deep-purple accent-4">
						Details
					</v-btn>
				</v-card-actions>
			</v-card>
			
			<v-card class="mx-auto" max-width="344">
				<v-card-text>
					<p class="display-1 text--primary">
						Vacation request
					</p>
				</v-card-text>
				<v-card-actions>
					<v-btn text color="deep-purple accent-4">
						Details
					</v-btn>
				</v-card-actions>
			</v-card>

			<v-card class="mx-auto" max-width="344">
				<v-card-text>
					<p class="display-1 text--primary">
						Profile
					</p>
				</v-card-text>
				<v-card-actions>
					<v-btn text color="deep-purple accent-4">
						Details
					</v-btn>
				</v-card-actions>
			</v-card>

			<v-card class="mx-auto" max-width="344">
				<v-card-text>
					<p class="display-1 text--primary">
						Appointment
					</p>
				</v-card-text>
				<v-card-actions>
					<v-btn text color="deep-purple accent-4">
						Details
					</v-btn>
				</v-card-actions>
			</v-card>
			
    	</v-container>
    	
    </div>
</template>

<script>
    import {mapActions, mapState} from "vuex";

    export default {
        name: "DoctorHomeComponent",
        components: {},
        data: () => ({
        }),
        computed: {
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
        },
        watch: {
        },
        methods: {
            
        },
        created() {
        	console.log("created");
			console.log(this.user);		//doctor id = user.id
			console.log(this.clinic);	//klinika kojoj ovaj doctor pripada
        }
    };

</script>
