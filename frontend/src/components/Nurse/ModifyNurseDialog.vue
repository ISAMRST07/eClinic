<template>
	<v-dialog
		:value="value"
		@input="$emit('input', $event)"
		persistent
		max-width="600px"
	>
		<v-card>
			<v-card-title>
				<span v-if="mode === 'add'" class="headline">Add a nurse</span>
				<span v-else class="headline">Update nurse</span>
			</v-card-title>
			<v-card-text>
				<v-container>
					<v-form ref="form">
						<v-row>
							<v-col cols="6">
								<v-text-field
									label="Email*"
									required
									:rules="emailRules"
									v-model="email"
								>
								</v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="Name*"
									required
									:rules="nameRules"
									v-model="name"
								>
								</v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="Surname*"
									required
									:rules="surnameRules"
									v-model="surname"
								></v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="Phone number"
									required
									:rules="phoneRules"
									v-model="phone"
								></v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="Address"
									required
									:rules="addressRules"
									v-model="address"
								></v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="City"
									required
									:rules="cityRules"
									v-model="city"
								></v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="Country"
									required
									:rules="countryRules"
									v-model="country"
								></v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="JMBG"
									required
									:rules="jmbgRules"
									v-model="jmbg"
								></v-text-field>
							</v-col>
							<v-col cols="6">
								<v-text-field
									label="Position*"
									required
									:rules="positionRules"
									v-model="position"
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
				<v-btn
					color="blue darken-1"
					v-if="mode === `add`"
					text
					@click="submit(addNurseApi)"
					>Add</v-btn
				>
				<v-btn 
					color="blue darken-1" 
					v-else 
					text 
					@click="submit(updateNurseApi)"
					>Update</v-btn
				>
				
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>

<script>

import {mapActions, mapState} from "vuex";
import { emptyNurse } from "../../utils/skeletons";
export default {
	name: "ModifyNurseDialog",
	components: {},
	data: () => ({
		email : null,
		name : null,
		surname : null,
		phone : null,
		address : null,
		city : null,
		country : null,
		jmbg : null,
		position: null,
		
		nurse: emptyNurse,

		showPassword: false,

		emailRules: [v => !!v || "Email is required."],
		nameRules: [v => !!v || "Name is required."],
		surnameRules: [v => !!v || "Surname is required"],
		phoneRules: [v => !!v || "Phone is required."],
		addressRules: [v => !!v || "Address is required."],
		cityRules: [v => !!v || "City is required"],
		countryRules: [v => !!v || "Country is required"],
		jmbgRules: [v => !!v || "JMBG is required"],
		positionRules: [v => !!v || "Position is required"]
		
	}),
	props: {
	    editNurse: null,
		value: false,
		mode: {
			type: String,
			default: "add"
		}
	},
	computed: {
		...mapState('auth', ['user']),
		...mapState('auth', ['clinic']),
	},
	watch: {
		value() {
        	if(this.editNurse) {
            	this.nurse = this.editNurse;
            	console.log("value changed");
            	console.log("nurseename = " + this.nurse.name);
            }
            this.email = this.nurse.email;
            this.name = this.nurse.name;
            this.surname = this.nurse.surname;
            this.phone = this.nurse.phone;
            this.address = this.nurse.address;
            this.city = this.nurse.city;
            this.country = this.nurse.country;
            this.jmbg = this.nurse.jmbg;
            this.position = this.nurse.position;
			console.log("thisname = " + this.name);
        }
	},
	methods: {
		...mapActions("nurse/nurse", ["addNurseApi"]),
		...mapActions("nurse/nurse", ["updateNurseApi"]),
		submit(fun) {
			console.log("addnurse or updatenurse pressed");
			console.log(fun);
			console.log(this.clinic.id);
			
			this.nurse.clinic = this.clinic.id;
			if (this.$refs.form.validate()) {
				this.nurse.clinic = this.clinic.id;
				this.nurse.email = this.email;
	            this.nurse.name = this.name;
	            this.nurse.surname = this.surname;
	            this.nurse.phone = this.phone;
	            this.nurse.address = this.address;
	            this.nurse.city = this.city;
	            this.nurse.country = this.country;
	            this.nurse.jmbg = this.jmbg;
	            this.nurse.position = this.position;
                fun(this.nurse);
                this.close();
			}
		},
		close() {
			console.log("close");
			this.$emit('input', false);
            if (this.mode === 'add') this.$refs.form.reset();
		},
		resetLayout() {
			console.log("resetlayout");
			this.nurse = emptyNurse;
			this.$refs.form.reset();
		}
	}
};
</script>

<style scoped></style>
