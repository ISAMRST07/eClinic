export function defaultError(err, commit) {
    if (err.response) {
        if (err.response.status === 401) {
            console.log("ERRRRRRRRR");
        }
    }
}
