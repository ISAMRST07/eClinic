<template>
    <v-container fluid>
    	<v-col cols="12">
            <v-textarea
              color="teal"
            >
              <template v-slot:label>
                <div>
                  Anamnesis
                </div>
              </template>
            </v-textarea>
          </v-col>
	      
	    <v-col cols="12">
  			 <disease-selection 
  			 	v-model="diagnoses" 
  			 	:multiple="true"/>
  	  	</v-col>            
  	  	<v-col cols="12">
  		 	<medicine-selection 
  		 		v-model="medicines"
  		 	  	:multiple="true"/>
  		 	
  	  	</v-col>      
  	    <v-col cols="12">
  		     <v-text-field
		          v-model="quantity"
		          label="Quantity"
		     ></v-text-field>  
  	    </v-col>
  	          	
	    <div class="my-2">
	        <v-btn small color="primary">Save</v-btn>
	    </div>
    </v-container>
</template>

<script>

	import DiseaseSelection from "../components/Disease/DiseaseSelection";
	import MedicineSelection from "../components/Medicine/MedicineSelection";

    export default {
        name: "Visit",
        components: { DiseaseSelection, MedicineSelection },
        data: () => ({
        	diagnoses: null,
        	medicines: null,
            quantity: 0
        }),
        
    }
</script>

<style scoped>

</style>

