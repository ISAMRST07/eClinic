import {redirectToLogin} from "../store/AuthModule";

export function defaultError(err, commit) {
    if(err.response) {
        if(err.response.status === 401) {
            console.log(commit);
            redirectToLogin(commit);
        }
    }
}
