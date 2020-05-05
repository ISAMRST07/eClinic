<template>
    <v-container
            class="fill-height"
            fluid
    >
        <v-row
                align="center"
                justify="center"
                class="mt-0"
        >
            <v-col
                    cols="12"
                    sm="8"
                    md="4"
            >
                <v-card outlined>
                    <v-container fluid class="pa-0">

                        <v-row align="center" class="mt-9" justify="center">
                            <v-col cols="12" md="8" class="text-center py-0"><span class="display-1">Sign in</span></v-col>
                        </v-row>
                        <v-row align="center" justify="center">
                            <v-col cols="12" md="8" class="text-center py-0"><span class="subtitle-1">to continue to eClinic</span></v-col>
                        </v-row>
                        <v-form @submit.prevent="postLogin">
                            <v-row class="ma-4 mb-0">
                                <v-col cols="12" class="pb-2">
                                        <v-text-field
                                                label="Email"
                                                name="email"
                                                :rules="[rules.required, rules.email]"
                                                v-model="authRequest.email"
                                                outlined
                                        />
                                </v-col>
                            </v-row>
                            <v-row class="mx-4 my-0">
                                <v-col cols="12" class="py-0">
                                    <v-text-field
                                            id="password"
                                            label="Password"
                                            name="password"
                                            :rules="[rules.required]"
                                            v-model="authRequest.password"
                                            type="password"
                                            outlined
                                    />
                                </v-col>
                            </v-row>
                            <v-row class="ma-4 mt-0">
                                <v-col cols="12" class="pt-2 align-end">
                                    <span class="text--secondary d-inline-flex">
                                        Don't have an account?

                                    </span>
                                    <v-btn type="submit" text color="primary" class="px-2" small>apply here</v-btn>
                                </v-col>
                            </v-row>
                            <v-row class="ma-4 mt-0">
                                <v-col cols="12" class="text-right">
                                    <v-btn depressed large color="primary">
                                        Sign in
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-form>

                    </v-container>
                </v-card>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
    import {emailPattern} from "../../utils/skeletons";
    import {mapActions, mapGetters} from "vuex";

    export default {
        name: "LoginDialog",
        data: () => ({
            authRequest: {
                email: null,
                password: null,
            },
            rules: {
                required: value => !!value || 'Required.',
                email: value => {
                    return emailPattern.test(value) || 'Invalid e-mail.';
                },
            }
        }),
        computed: {
            ...mapGetters('auth', ['loggedIn']),
        },
        watch: {
            loggedIn(val) {
                if (val) {
                    this.$router.push('/');
                }
            }
        },
        methods: {
            ...mapActions('auth', ['login']),
            postLogin(event) {
                this.login(this.authRequest);
            }
        }
    }
</script>

<style scoped>

</style>
