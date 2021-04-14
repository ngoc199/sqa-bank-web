/**
 * Update the query parameters using the form value.
 * This function must be called in the onsubmit event
 * @param {event} e
 */
function formUpdateParams(e) {
  e.preventDefault();

  // Get the form in the event.target, because this is called in onsubmit event, the target is the form
  let form = e.target;
  let formData = new FormData(form);
  let url = new URL(window.location.href);
  let searchParams = url.searchParams;
  for (let [key, value] of formData.entries()) {
    searchParams.set(key, value);
  }
  window.location.href = url.toString();
}
