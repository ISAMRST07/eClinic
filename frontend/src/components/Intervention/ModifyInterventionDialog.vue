<template>
    <v-dialog
            :value="value"
            @input="$emit('input', $event)"
            persistent max-width="700px">
        <v-card>
            <v-card-title>
                <span v-if="mode === 'add'" class="headline">Add a intervention</span>
                <span v-else class="headline">Update intervention</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-form ref="form">
                        <v-row>
                            
                           <v-col cols="6">
	                            <v-date-picker 
	                            	v-model="date"
	                            	:rules="dateRules"
	                            	color="green lighten-1">
	                            </v-date-picker>  
                           </v-col>
                           <v-col cols="6">
							   <v-time-picker
							   		v-model="time"
	                            	:rules="timeRules"					   		
							   		color="green lighten-1">
							   	</v-time-picker>
                           </v-col>
							
							<v-col cols="12">
                                <clinic-room-selection 
	                                v-model="selectedClinicRoom"
	                                :disabled="mode === `update`"/>
                            </v-col>  
                               
                            <v-col cols="12">
                                <doctor-selection 
	                                v-model="selectedDoctor"
	                                :disabled="mode === `update`"/>
                            </v-col>
                            
                            <v-col cols="6">
                                <intervention-type-selection 
                                	v-model="selectedInterventionType"
                                	:disabled="mode === `update`"/>
                            </v-col> 
                            
                            <v-col cols="6">
								<v-text-field
									label="Price for intervention(in $)*"
									required
									disabled
									:value="selectedInterventionType.price"
								>
								</v-text-field>
							</v-col>   
                            <v-col cols="6">
								<v-text-field
									label="Duration (in minutes)*"
									required
									:rules="durationRules"
									v-model="duration"
								>
								</v-text-field>
							</v-col>     
							<v-col cols="6">
								<v-text-field
									label="Price (in $)*"
									required
									:rules="priceRules"
									v-model="price"
								>
								</v-text-field>
							</v-col>      
                        </v-row>
                    </v-form>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Close</v-btn>
                <v-btn color="blue darken-1" v-if="mode === `add`" text @click="submit(addInterventionApi)">Add</v-btn>
                <v-btn color="blue darken-1" v-else text @click="submit(updateInterventionApi)">Update</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import {mapActions, mapState} from "vuex";
    import {emptyIntervention} from "../../utils/skeletons";
    import ClinicRoomSelection from "../ClinicRooms/ClinicRoomSelection";
    import DoctorSelection from "../Doctor/DoctorSelection";
    import InterventionTypeSelection from "../InterventionType/InterventionTypeSelection";

    export default {
        name: "ModifyInterventionDialog",
        components: {ClinicRoomSelection, DoctorSelection, InterventionTypeSelection},
        data: () => ({
            intervention: emptyIntervention,
            date : '',
            time : '',
            selectedClinicRoom: '',    
            selectedDoctor: '',   
            selectedInterventionType: '', 
            duration: '',  
            price: '',       
            dateRules: [v => !!v || 'Date is required.'],
            timeRules: [v => !!v || 'Time is required.'],
            durationRules: [v => !!v || 'Duration is required.'],
            priceRules: [v => !!v || 'Price is required.']
        }),
        props: {
            editIntervention: null,
            value: false,
            mode: {
                type: String,
                default: 'add'
            }
        },
        computed: {
            ...mapState('auth', ['user']),
            ...mapState('auth', ['clinic']),
        },
        watch: {
            value() {
                if (this.editIntervention) {
                    console.log("value changed editIntervention");
                    console.log(this.editIntervention.date);
                    this.intervention = this.editIntervention;
                }
                this.date = this.intervention.date;
                this.time = this.intervention.date;
                this.selectedClinicRoom = this.intervention.selectedClinicRoom;
                this.selectedDoctor = this.intervention.selectedDoctor;
                this.selectedInterventionType = this.intervention.selectedInterventionType;
                this.duration = this.intervention.duration;
                this.price = this.intervention.price;  
               	console.log(this.date); 
            }
        },
        methods: {
            ...mapActions('intervention/intervention', ['addInterventionApi']),
            ...mapActions('intervention/intervention', ['updateInterventionApi']),

            submit(fun) {
                if (this.$refs.form.validate()) {
                	console.log("add pressed");   
                	console.log(this.date);
                	console.log(this.time);
                	console.log(this.selectedClinicRoom.name);
                  	console.log(this.selectedDoctor.user.name); 	
                    console.log(this.selectedInterventionType.name);
                    console.log(this.duration);
                    console.log(this.price);
                    
                    this.intervention.date = this.date;
                    this.intervention.time = this.date;
                    this.intervention.selectedClinicRoom = this.selectedClinicRoom;
                    this.intervention.selectedDoctor = this.selectedDoctor;
                    this.intervention.selectedInterventionType = this.selectedInterventionType;
                    this.intervention.duration = this.duration;
                    this.intervention.price = this.price;   
                    console.log("intervention = ");
                    console.log(this.intervention);   
                    fun(this.intervention);
                    this.close();
                }
            },
            close() {
                this.$emit('input', false);
                if (this.mode === 'add') this.$refs.form.reset();
            }
        }
    }
</script>

<style scoped>

</style>
