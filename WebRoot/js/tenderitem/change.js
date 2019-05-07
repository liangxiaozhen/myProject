$(document).ready(function() {
	$("body").on("change", "#type1", function(e) {
		if($(this).val() == ("iequota" + 1)) {
			$("#dayawardrate_div" + 1 + " " + "input").val("");
			$("#dayawardrate_div" + 1).hide();
			$("#quotainput_div" + 1).show();
		} else if($(this).val() == ("iepercent" + 1)) {
			$("#dayawardrate_div" + 1).show();
			$("#quotainput_div" + 1 + " " + "input").val("");
			$("#quotainput_div" + 1).hide();
		} else {
			$("#quotainput_div" + 1).hide();
			$("#dayawardrate_div" + 1).hide();
			$("#quotainput_div" + 1 + " " + "input").val("");
			$("#dayawardrate_div" + 1 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type2", function(e) {
		if($(this).val() == ("iequota" + 2)) {
			$("#dayawardrate_div" + 2 + " " + "input").val("");
			$("#dayawardrate_div" + 2).hide();
			$("#quotainput_div" + 2).show();
		} else if($(this).val() == ("iepercent" + 2)) {
			$("#dayawardrate_div" + 2).show();
			$("#quotainput_div" + 2 + " " + "input").val("");
			$("#quotainput_div" + 2).hide();
		} else {
			$("#quotainput_div" + 2).hide();
			$("#dayawardrate_div" + 2).hide();
			$("#quotainput_div" + 2 + " " + "input").val("");
			$("#dayawardrate_div" + 2 + " " + "input").val("");
		}
	});

	$("body").on("change", "#type3", function(e) {
		if($(this).val() == ("iequota" + 3)) {
			$("#dayawardrate_div" + 3 + " " + "input").val("");
			$("#dayawardrate_div" + 3).hide();
			$("#quotainput_div" + 3).show();
		} else if($(this).val() == ("iepercent" + 3)) {
			$("#dayawardrate_div" + 3).show();
			$("#quotainput_div" + 3 + " " + "input").val("");
			$("#quotainput_div" + 3).hide();
		} else {
			$("#quotainput_div" + 3).hide();
			$("#dayawardrate_div" + 3).hide();
			$("#quotainput_div" + 3 + " " + "input").val("");
			$("#dayawardrate_div" + 3 + " " + "input").val("");
		}
	});
	
	$("body").on("change", "#type4", function(e) {
		if($(this).val() == ("iequota" + 4)) {
			$("#dayawardrate_div" + 4 + " " + "input").val("");
			$("#dayawardrate_div" + 4).hide();
			$("#quotainput_div" + 4).show();
		} else if($(this).val() == ("iepercent" + 4)) {
			$("#dayawardrate_div" + 4).show();
			$("#quotainput_div" + 4 + " " + "input").val("");
			$("#quotainput_div" + 4).hide();
		} else {
			$("#quotainput_div" + 4).hide();
			$("#dayawardrate_div" + 4).hide();
			$("#quotainput_div" + 4 + " " + "input").val("");
			$("#dayawardrate_div" + 4 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type5", function(e) {
		if($(this).val() == ("iequota" + 5)) {
			$("#dayawardrate_div" + 5 + " " + "input").val("");
			$("#dayawardrate_div" + 5).hide();
			$("#quotainput_div" + 5).show();
		} else if($(this).val() == ("iepercent" + 5)) {
			$("#dayawardrate_div" + 5).show();
			$("#quotainput_div" + 5 + " " + "input").val("");
			$("#quotainput_div" + 5).hide();
		} else {
			$("#quotainput_div" + 5).hide();
			$("#dayawardrate_div" + 5).hide();
			$("#quotainput_div" + 5 + " " + "input").val("");
			$("#dayawardrate_div" + 5 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type6", function(e) {
		if($(this).val() == ("iequota" + 6)) {
			$("#dayawardrate_div" + 6 + " " + "input").val("");
			$("#dayawardrate_div" + 6).hide();
			$("#quotainput_div" + 6).show();
		} else if($(this).val() == ("iepercent" + 6)) {
			$("#dayawardrate_div" + 6).show();
			$("#quotainput_div" + 6 + " " + "input").val("");
			$("#quotainput_div" + 6).hide();
		} else {
			$("#quotainput_div" + 6).hide();
			$("#dayawardrate_div" + 6).hide();
			$("#quotainput_div" + 6 + " " + "input").val("");
			$("#dayawardrate_div" + 6 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type7", function(e) {
		if($(this).val() == ("iequota" + 7)) {
			$("#dayawardrate_div" + 7 + " " + "input").val("");
			$("#dayawardrate_div" + 7).hide();
			$("#quotainput_div" + 7).show();
		} else if($(this).val() == ("iepercent" + 7)) {
			$("#dayawardrate_div" + 7).show();
			$("#quotainput_div" + 7 + " " + "input").val("");
			$("#quotainput_div" + 7).hide();
		} else {
			$("#quotainput_div" + 7).hide();
			$("#dayawardrate_div" + 7).hide();
			$("#quotainput_div" + 7 + " " + "input").val("");
			$("#dayawardrate_div" + 7 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type8", function(e) {
		if($(this).val() == ("iequota" + 8)) {
			$("#dayawardrate_div" + 8 + " " + "input").val("");
			$("#dayawardrate_div" + 8).hide();
			$("#quotainput_div" + 8).show();
		} else if($(this).val() == ("iepercent" + 8)) {
			$("#dayawardrate_div" + 8).show();
			$("#quotainput_div" + 8 + " " + "input").val("");
			$("#quotainput_div" + 8).hide();
		} else {
			$("#quotainput_div" + 8).hide();
			$("#dayawardrate_div" + 8).hide();
			$("#quotainput_div" + 8 + " " + "input").val("");
			$("#dayawardrate_div" + 8 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type9", function(e) {
		if($(this).val() == ("iequota" + 9)) {
			$("#dayawardrate_div" + 9 + " " + "input").val("");
			$("#dayawardrate_div" + 9).hide();
			$("#quotainput_div" + 9).show();
		} else if($(this).val() == ("iepercent" + 9)) {
			$("#dayawardrate_div" + 9).show();
			$("#quotainput_div" + 9 + " " + "input").val("");
			$("#quotainput_div" + 9).hide();
		} else {
			$("#quotainput_div" + 9).hide();
			$("#dayawardrate_div" + 9).hide();
			$("#quotainput_div" + 9 + " " + "input").val("");
			$("#dayawardrate_div" + 9 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type10", function(e) {
		if($(this).val() == ("iequota" + 10)) {
			$("#dayawardrate_div" + 10 + " " + "input").val("");
			$("#dayawardrate_div" + 10).hide();
			$("#quotainput_div" + 10).show();
		} else if($(this).val() == ("iepercent" + 10)) {
			$("#dayawardrate_div" + 10).show();
			$("#quotainput_div" + 10 + " " + "input").val("");
			$("#quotainput_div" + 10).hide();
		} else {
			$("#quotainput_div" + 10).hide();
			$("#dayawardrate_div" + 10).hide();
			$("#quotainput_div" + 10 + " " + "input").val("");
			$("#dayawardrate_div" + 10 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type11", function(e) {
		if($(this).val() == ("iequota" + 11)) {
			$("#dayawardrate_div" + 11 + " " + "input").val("");
			$("#dayawardrate_div" + 11).hide();
			$("#quotainput_div" + 11).show();
		} else if($(this).val() == ("iepercent" + 11)) {
			$("#dayawardrate_div" + 11).show();
			$("#quotainput_div" + 11 + " " + "input").val("");
			$("#quotainput_div" + 11).hide();
		} else {
			$("#quotainput_div" + 11).hide();
			$("#dayawardrate_div" + 11).hide();
			$("#quotainput_div" + 11 + " " + "input").val("");
			$("#dayawardrate_div" + 11 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type12", function(e) {
		if($(this).val() == ("iequota" + 12)) {
			$("#dayawardrate_div" + 12 + " " + "input").val("");
			$("#dayawardrate_div" + 12).hide();
			$("#quotainput_div" + 12).show();
		} else if($(this).val() == ("iepercent" + 12)) {
			$("#dayawardrate_div" + 12).show();
			$("#quotainput_div" + 12 + " " + "input").val("");
			$("#quotainput_div" + 12).hide();
		} else {
			$("#quotainput_div" + 12).hide();
			$("#dayawardrate_div" + 12).hide();
			$("#quotainput_div" + 12 + " " + "input").val("");
			$("#dayawardrate_div" + 12 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type13", function(e) {
		if($(this).val() == ("iequota" + 13)) {
			$("#dayawardrate_div" + 13 + " " + "input").val("");
			$("#dayawardrate_div" + 13).hide();
			$("#quotainput_div" + 13).show();
		} else if($(this).val() == ("iepercent" + 13)) {
			$("#dayawardrate_div" + 13).show();
			$("#quotainput_div" + 13 + " " + "input").val("");
			$("#quotainput_div" + 13).hide();
		} else {
			$("#quotainput_div" + 13).hide();
			$("#dayawardrate_div" + 13).hide();
			$("#quotainput_div" + 13 + " " + "input").val("");
			$("#dayawardrate_div" + 13 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type14", function(e) {
		if($(this).val() == ("iequota" + 14)) {
			$("#dayawardrate_div" + 14 + " " + "input").val("");
			$("#dayawardrate_div" + 14).hide();
			$("#quotainput_div" + 14).show();
		} else if($(this).val() == ("iepercent" + 14)) {
			$("#dayawardrate_div" + 14).show();
			$("#quotainput_div" + 14 + " " + "input").val("");
			$("#quotainput_div" + 14).hide();
		} else {
			$("#quotainput_div" + 14).hide();
			$("#dayawardrate_div" + 14).hide();
			$("#quotainput_div" + 14 + " " + "input").val("");
			$("#dayawardrate_div" + 14 + " " + "input").val("");
		}
	});
	$("body").on("change", "#type15", function(e) {
		if($(this).val() == ("iequota" + 15)) {
			$("#dayawardrate_div" + 15 + " " + "input").val("");
			$("#dayawardrate_div" + 15).hide();
			$("#quotainput_div" + 15).show();
		} else if($(this).val() == ("iepercent" + 15)) {
			$("#dayawardrate_div" + 15).show();
			$("#quotainput_div" + 15 + " " + "input").val("");
			$("#quotainput_div" + 15).hide();
		} else {
			$("#quotainput_div" + 15).hide();
			$("#dayawardrate_div" + 15).hide();
			$("#quotainput_div" + 15 + " " + "input").val("");
			$("#dayawardrate_div" + 15 + " " + "input").val("");
		}
	});
});