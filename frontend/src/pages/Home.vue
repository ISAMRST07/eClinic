<template>
	<v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">You must update yout password</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
            	<v-col cols="12">
	                <v-text-field 
	                	v-model="currentPassword" 
	                	label="Current password*"
	                	:append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
	                    :type="showPassword ? 'text' : 'password'"
	                    :error="oldPasswordError.isError"
                        :error-messages="oldPasswordError.errorMessage"
	                    @click:append="showPassword = !showPassword"
	                	:rules="[rules.required]"
	                	outlined
	                	required>
	                </v-text-field>
              	</v-col>
	              <v-col cols="12">
	                <v-text-field 
	                	v-model="password" 
	                	label="Password*"
	                	:append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
	                    :type="showPassword ? 'text' : 'password'"
	                    @click:append="showPassword = !showPassword"
	                	:rules="[rules.required]"
	                	outlined
	                	required>
	                </v-text-field>
	              </v-col>
	              <v-col cols="12">
	                <v-text-field 
	                	v-model="repeatedPassword" 
	                	label="Repeat Password*" 
	                	:type="showRepeated ? 'text' : 'password'"
	                    :append-icon="showRepeated ? 'mdi-eye' : 'mdi-eye-off'"
	                    @click:append="showRepeated = !showRepeated"
	                	:rules="[rules.required, repeatedRule]"
	                	outlined
	                	required>
	                </v-text-field>
	              </v-col>
            </v-row>
          </v-container>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
			<v-spacer></v-spacer>
          	<v-btn color="blue darken-1" text @click="save()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
    import {mapActions, mapState} from "vuex";

    export default {
        name: "Home",
        data: () => ({
        	dialog: false,
        	password: '',
        	repeatedPassword: '',
        	currentPassword: '',
            showRepeated: false,
            oldPasswordError: {
                isError: false,
                errorMessage: '',
            },
        	rules: {
                required: v => !!v || 'Required!',
                min8: v=> !!v && v.length >= 8 || 'At least 8 characters',
            }
        }),
        computed: {
            ...mapState('auth', ['user']),
            repeatedRule() {
                return () => this.password === this.repeatedPassword || `Doesn't match password`;
            }
        
        },
        props: {
            personal: {
                type: Boolean,
                default: false
            }
        },
        methods: {
         	save(){
         		this.oldPasswordError.isError = false;
                this.oldPasswordError.errorMessage = '';
         		this.changePassword();
         	},
         	async changePassword() {
                try {
                	console.log("put changepassword");
                    let {data: res} = await this.$axios.put(`/api/auth/changepassword/${this.user.id}`,
                        {
                            oldPassword: this.currentPassword,
                            newPassword: this.password,
                            personal: this.personal
                        });
                        console.log("commit");
                        console.log(res);
                    this.$store.commit('auth/updateResponse', res);
                    this.dialog = false;
                } catch (err) {
                    if(err.response.status === 400) {
                        this.oldPasswordError.isError = true;
                        this.oldPasswordError.errorMessage = 'The old password isn\'t correct';
                    }
                }
            }
        },
        created() {
        	console.log(this.user);
        	if(this.user.lastPasswordResetDate == null){
        		console.log("lastPasswordResetDate = null");
        		this.dialog = true;
        	}else{
        		console.log("lastPasswordResetDate = " + this.user.lastPasswordResetDate);        	
				this.dialog = false;
        	}
        }
    }
</script>

<style scoped>

</style>
