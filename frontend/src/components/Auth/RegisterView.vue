<template>
    <v-container fluid class="pa-0">
        <v-stepper v-model="registerStep" class="elevation-0 pa-0">
            <v-stepper-items>
                <v-stepper-content step="1" class="pa-0">
                    <v-row align="center" class="mt-9" justify="center">
                        <v-col cols="12" md="8" class="text-center py-0"><span class="display-1">Sign up</span></v-col>
                    </v-row>
                    <v-row align="center" justify="center">
                        <v-col cols="12" md="8" class="text-center py-0">
                            <span class="subtitle-1">to get a new account</span>
                        </v-col>
                    </v-row>
                    <v-row class="ma-4 mb-0">
                        <v-col cols="12" md="8" class="pb-0">
                            <span class="grey--text text--darken-1">First, choose your email:</span>
                        </v-col>
                    </v-row>
                    <v-form ref="emailForm" lazy-validation>
                        <v-row class="ma-4 mb-0">
                            <v-col cols="12" class="pb-2">
                                <v-text-field
                                        label="Email"
                                        name="email"
                                        :rules="[rules.required, rules.email]"
                                        v-model="formElements.email"
                                        outlined
                                />
                            </v-col>
                        </v-row>
                        <v-row class="ma-4 mt-0">
                            <v-col cols="12" class="pt-2 align-end">
                                                            <span class="text--secondary d-inline-flex">
                                                                Already have an account?
                                                            </span>
                                <v-btn text color="primary" @click="$emit('loginClick')" class="px-2" small>
                                    sign in
                                </v-btn>
                            </v-col>
                        </v-row>
                        <v-row class="ma-4 mt-0">
                            <v-col cols="12" class="text-right">
                                <v-btn depressed large color="primary">
                                    Next
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-stepper-content>
                <v-stepper-content step="2" class="pa-0">
                    <v-row align="center" class="mt-9" justify="center">
                        <v-col cols="12" md="8" class="text-center py-0">
                            <span v-if="name" class="subtitle-1">We are glad that you chose our clinical center</span>
                        </v-col>
                    </v-row>
                    <v-row align="center" justify="center">
                        <v-col cols="12" md="8" class="text-center py-0">
                            <span class="subtitle-1">{{ authRequest.email }}</span>
                        </v-col>
                    </v-row>
                    <v-row class="ma-4 mb-0">
                        <v-col cols="12" md="8" class="pb-0">
                            <span class="grey--text text--darken-1">Now, choose your password.</span>
                        </v-col>
                    </v-row>
                    <v-form ref="passwordForm" @submit.prevent="passwordSelected" lazy-validation>
                        <v-row class="ma-4 mt-0 mb-0">
                            <v-col cols="12" class="pb-2">
                                <v-text-field
                                        label="Password"
                                        name="password"
                                        :rules="[rules.required]"
                                        :error="passwordError.isError"
                                        :error-messages="passwordError.errorMessage"
                                        v-model="formElements.password"
                                        type="password"
                                        outlined
                                />
                            </v-col>
                        </v-row>

                        <v-row class="ma-4 mt-0 mb-0">
                            <v-col cols="12" class="pb-2">
                                <v-text-field
                                        label="Repeat password"
                                        name="repeatPassword"
                                        :rules="[rules.required]"
                                        :error="passwordError.isError"
                                        :error-messages="passwordError.errorMessage"
                                        v-model="formElements.repeatPassword"
                                        type="password"
                                        outlined
                                />
                            </v-col>
                        </v-row>

                        <v-row class="ma-4 mt-0">
                            <v-col cols="12" class="pt-2 align-end">
                                <span class="text--secondary d-inline-flex">
                                    Don't worry. We won't tell anyone.
                                </span>
                            </v-col>
                        </v-row>
                        <v-row class="ma-4 mt-0" justify="space-between">
                            <v-col cols="4">
                                <v-btn text type="button" large color="secondary" @click="returnToEmail">
                                    Go back
                                </v-btn>
                            </v-col>
                            <v-col cols="4">
                                <v-btn type="submit" depressed large color="primary">
                                    Sign in
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-form>
                </v-stepper-content>
            </v-stepper-items>
        </v-stepper>
    </v-container>
</template>

<script>
    import {emailPattern} from "../../utils/skeletons";

    export default {
        name: "RegisterView",
        data: () => ({
            registerStep: 1,
            newUser: {
                email: null,
                password: null
            },
            formElements: {
                email: null,
                password: null,
                repeatPassword: null,
            },
            emailError: {
                isError: false,
                errorMessage: ''
            },
            passwordError: {
                isError: false,
                errorMessage: ''
            },
            rules: {
                required: value => !!value || 'Required.',
                email: value => {
                    return emailPattern.test(value) || 'Invalid e-mail.';
                },
            }
        }),
        computed: {
            // ...mapGetters('auth', ['loggedIn']),
        },
        watch: {
            // loggedIn(val) {
            //     if (val) {
            //         this.$router.push('/');
            //     }
            // }
        },
        methods: {
            // ...mapActions('auth', ['login']),
            // postLogin(event) {
            //     this.login(this.authRequest);
            // }
        }
    }
</script>

<style scoped>

</style>
