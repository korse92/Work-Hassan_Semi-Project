// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable({
  	lengthMenu: [5, 25, 50, 100],
  	lengthChange: false,
  	info: false,
  	ordering: true,
  	});
});
