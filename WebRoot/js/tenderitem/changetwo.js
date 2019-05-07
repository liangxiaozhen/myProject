$(document).ready(function() {
	//奖励方式
	$("body").on("change", "#inputawardtype1", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 1 + " " + "select").val("");
			$("#paward_div" + 1).hide();
			$("#loanpp_div" + 1).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 1 + " " + "input").val("");
			$("#loanpp_div" + 1 + " " + "select").val("");
			$("#loanpp_div" + 1).hide();
			$("#paward_div" + 1).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 1 + " " + "input").val("");
			$("#paward_div" + 1 + " " + "select").val("");
			$("#loanpp_div" + 1 + " " + "select").val("");
			$("#loanpp_div" + 1).show();
			$("#paward_div" + 1).show();
		} else {
			$("#paward_div" + 1 + " " + "select").val("");
			$("#loanpp_div" + 1 + " " + "input").val("");
			$("#loanpp_div" + 1 + " " + "select").val("");
			$("#loanpp_div" + 1).hide();
			$("#paward_div" + 1).hide();
		}
	});
	$("body").on("change", "#inputawardtype2", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 2 + " " + "select").val("");
			$("#paward_div" + 2).hide();
			$("#loanpp_div" + 2).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 2 + " " + "input").val("");
			$("#loanpp_div" + 2 + " " + "select").val("");
			$("#loanpp_div" + 2).hide();
			$("#paward_div" + 2).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 2 + " " + "input").val("");
			$("#paward_div" + 2 + " " + "select").val("");
			$("#loanpp_div" + 2 + " " + "select").val("");
			$("#loanpp_div" + 2).show();
			$("#paward_div" + 2).show();
		} else {
			$("#paward_div" + 2 + " " + "select").val("");
			$("#loanpp_div" + 2 + " " + "input").val("");
			$("#loanpp_div" + 2 + " " + "select").val("");
			$("#loanpp_div" + 2).hide();
			$("#paward_div" + 2).hide();
		}
	});
	$("body").on("change", "#inputawardtype3", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 3 + " " + "select").val("");
			$("#paward_div" + 3).hide();
			$("#loanpp_div" + 3).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 3 + " " + "input").val("");
			$("#loanpp_div" + 3 + " " + "select").val("");
			$("#loanpp_div" + 3).hide();
			$("#paward_div" + 3).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 3 + " " + "input").val("");
			$("#paward_div" + 3 + " " + "select").val("");
			$("#loanpp_div" + 3 + " " + "select").val("");
			$("#loanpp_div" + 3).show();
			$("#paward_div" + 3).show();
		} else {
			$("#paward_div" + 3 + " " + "select").val("");
			$("#loanpp_div" + 3 + " " + "input").val("");
			$("#loanpp_div" + 3 + " " + "select").val("");
			$("#loanpp_div" + 3).hide();
			$("#paward_div" + 3).hide();
		}
	});
	$("body").on("change", "#inputawardtype4", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 4 + " " + "select").val("");
			$("#paward_div" + 4).hide();
			$("#loanpp_div" + 4).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 4 + " " + "input").val("");
			$("#loanpp_div" + 4 + " " + "select").val("");
			$("#loanpp_div" + 4).hide();
			$("#paward_div" + 4).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 4 + " " + "input").val("");
			$("#paward_div" + 4 + " " + "select").val("");
			$("#loanpp_div" + 4 + " " + "select").val("");
			$("#loanpp_div" + 4).show();
			$("#paward_div" + 4).show();
		} else {
			$("#paward_div" + 4 + " " + "select").val("");
			$("#loanpp_div" + 4 + " " + "input").val("");
			$("#loanpp_div" + 4 + " " + "select").val("");
			$("#loanpp_div" + 4).hide();
			$("#paward_div" + 4).hide();
		}
	});
	$("body").on("change", "#inputawardtype5", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 5 + " " + "select").val("");
			$("#paward_div" + 5).hide();
			$("#loanpp_div" + 5).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 5 + " " + "input").val("");
			$("#loanpp_div" + 5 + " " + "select").val("");
			$("#loanpp_div" + 5).hide();
			$("#paward_div" + 5).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 5 + " " + "input").val("");
			$("#paward_div" + 5 + " " + "select").val("");
			$("#loanpp_div" + 5 + " " + "select").val("");
			$("#loanpp_div" + 5).show();
			$("#paward_div" + 5).show();
		} else {
			$("#paward_div" + 5 + " " + "select").val("");
			$("#loanpp_div" + 5 + " " + "input").val("");
			$("#loanpp_div" + 5 + " " + "select").val("");
			$("#loanpp_div" + 5).hide();
			$("#paward_div" + 5).hide();
		}
	});
	$("body").on("change", "#inputawardtype6", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 6 + " " + "select").val("");
			$("#paward_div" + 6).hide();
			$("#loanpp_div" + 6).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 6 + " " + "input").val("");
			$("#loanpp_div" + 6 + " " + "select").val("");
			$("#loanpp_div" + 6).hide();
			$("#paward_div" + 6).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 6 + " " + "input").val("");
			$("#paward_div" + 6 + " " + "select").val("");
			$("#loanpp_div" + 6 + " " + "select").val("");
			$("#loanpp_div" + 6).show();
			$("#paward_div" + 6).show();
		} else {
			$("#paward_div" + 6 + " " + "select").val("");
			$("#loanpp_div" + 6 + " " + "input").val("");
			$("#loanpp_div" + 6 + " " + "select").val("");
			$("#loanpp_div" + 6).hide();
			$("#paward_div" + 6).hide();
		}
	});
	$("body").on("change", "#inputawardtype7", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 7 + " " + "select").val("");
			$("#paward_div" + 7).hide();
			$("#loanpp_div" + 7).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 7 + " " + "input").val("");
			$("#loanpp_div" + 7 + " " + "select").val("");
			$("#loanpp_div" + 7).hide();
			$("#paward_div" + 7).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 7 + " " + "input").val("");
			$("#paward_div" + 7 + " " + "select").val("");
			$("#loanpp_div" + 7 + " " + "select").val("");
			$("#loanpp_div" + 7).show();
			$("#paward_div" + 7).show();
		} else {
			$("#paward_div" + 7 + " " + "select").val("");
			$("#loanpp_div" + 7 + " " + "input").val("");
			$("#loanpp_div" + 7 + " " + "select").val("");
			$("#loanpp_div" + 7).hide();
			$("#paward_div" + 7).hide();
		}
	});
	$("body").on("change", "#inputawardtype8", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 8 + " " + "select").val("");
			$("#paward_div" + 8).hide();
			$("#loanpp_div" + 8).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 8 + " " + "input").val("");
			$("#loanpp_div" + 8 + " " + "select").val("");
			$("#loanpp_div" + 8).hide();
			$("#paward_div" + 8).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 8 + " " + "input").val("");
			$("#paward_div" + 8 + " " + "select").val("");
			$("#loanpp_div" + 8 + " " + "select").val("");
			$("#loanpp_div" + 8).show();
			$("#paward_div" + 8).show();
		} else {
			$("#paward_div" + 8 + " " + "select").val("");
			$("#loanpp_div" + 8 + " " + "input").val("");
			$("#loanpp_div" + 8 + " " + "select").val("");
			$("#loanpp_div" + 8).hide();
			$("#paward_div" + 8).hide();
		}
	});
	$("body").on("change", "#inputawardtype9", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 9 + " " + "select").val("");
			$("#paward_div" + 9).hide();
			$("#loanpp_div" + 9).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 9 + " " + "input").val("");
			$("#loanpp_div" + 9 + " " + "select").val("");
			$("#loanpp_div" + 9).hide();
			$("#paward_div" + 9).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 9 + " " + "input").val("");
			$("#paward_div" + 9 + " " + "select").val("");
			$("#loanpp_div" + 9 + " " + "select").val("");
			$("#loanpp_div" + 9).show();
			$("#paward_div" + 9).show();
		} else {
			$("#paward_div" + 9 + " " + "select").val("");
			$("#loanpp_div" + 9 + " " + "input").val("");
			$("#loanpp_div" + 9 + " " + "select").val("");
			$("#loanpp_div" + 9).hide();
			$("#paward_div" + 9).hide();
		}
	});
	$("body").on("change", "#inputawardtype10", function(e) {
		if($(this).val() == 1) { //惩罚借款人
			$("#paward_div" + 10 + " " + "select").val("");
			$("#paward_div" + 10).hide();
			$("#loanpp_div" + 10).show();
		} else if($(this).val() == 2) { //平太奖励
			$("#loanpp_div" + 10 + " " + "input").val("");
			$("#loanpp_div" + 10 + " " + "select").val("");
			$("#loanpp_div" + 10).hide();
			$("#paward_div" + 10).show();
		} else if($(this).val() == 3) {
			$("#loanpp_div" + 10 + " " + "input").val("");
			$("#paward_div" + 10 + " " + "select").val("");
			$("#loanpp_div" + 10 + " " + "select").val("");
			$("#loanpp_div" + 10).show();
			$("#paward_div" + 10).show();
		} else {
			$("#paward_div" + 10 + " " + "select").val("");
			$("#loanpp_div" + 10 + " " + "input").val("");
			$("#loanpp_div" + 10 + " " + "select").val("");
			$("#loanpp_div" + 10).hide();
			$("#paward_div" + 10).hide();
		}
	});
	//类型
	$('body').on("change", "#typethr1", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 1 + " " + "input").val("");
			$("#dayawardrate_divthr" + 1).hide();
			$("#quotainput_divthr" + 1).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 1).show();
			$("#quotainput_divthr" + 1 + " " + "input").val("");
			$("#quotainput_divthr" + 1).hide();
		} else {
			$("#quotainput_divthr" + 1).hide();
			$("#dayawardrate_divthr" + 1).hide();
			$("#quotainput_divthr" + 1 + " " + "input").val("");
			$("#dayawardrate_divthr" + 1 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr2", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 2 + " " + "input").val("");
			$("#dayawardrate_divthr" + 2).hide();
			$("#quotainput_divthr" + 2).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 2).show();
			$("#quotainput_divthr" + 2 + " " + "input").val("");
			$("#quotainput_divthr" + 2).hide();
		} else {
			$("#quotainput_divthr" + 2).hide();
			$("#dayawardrate_divthr" + 2).hide();
			$("#quotainput_divthr" + 2 + " " + "input").val("");
			$("#dayawardrate_divthr" + 2 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr3", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 3 + " " + "input").val("");
			$("#dayawardrate_divthr" + 3).hide();
			$("#quotainput_divthr" + 3).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 3).show();
			$("#quotainput_divthr" + 3 + " " + "input").val("");
			$("#quotainput_divthr" + 3).hide();
		} else {
			$("#quotainput_divthr" + 3).hide();
			$("#dayawardrate_divthr" + 3).hide();
			$("#quotainput_divthr" + 3 + " " + "input").val("");
			$("#dayawardrate_divthr" + 3 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr4", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 4 + " " + "input").val("");
			$("#dayawardrate_divthr" + 4).hide();
			$("#quotainput_divthr" + 4).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 4).show();
			$("#quotainput_divthr" + 4 + " " + "input").val("");
			$("#quotainput_divthr" + 4).hide();
		} else {
			$("#quotainput_divthr" + 4).hide();
			$("#dayawardrate_divthr" + 4).hide();
			$("#quotainput_divthr" + 4 + " " + "input").val("");
			$("#dayawardrate_divthr" + 4 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr5", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 5 + " " + "input").val("");
			$("#dayawardrate_divthr" + 5).hide();
			$("#quotainput_divthr" + 5).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 5).show();
			$("#quotainput_divthr" + 5 + " " + "input").val("");
			$("#quotainput_divthr" + 5).hide();
		} else {
			$("#quotainput_divthr" + 5).hide();
			$("#dayawardrate_divthr" + 5).hide();
			$("#quotainput_divthr" + 5 + " " + "input").val("");
			$("#dayawardrate_divthr" + 5 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr6", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 6 + " " + "input").val("");
			$("#dayawardrate_divthr" + 6).hide();
			$("#quotainput_divthr" + 6).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 6).show();
			$("#quotainput_divthr" + 6 + " " + "input").val("");
			$("#quotainput_divthr" + 6).hide();
		} else {
			$("#quotainput_divthr" + 6).hide();
			$("#dayawardrate_divthr" + 6).hide();
			$("#quotainput_divthr" + 6 + " " + "input").val("");
			$("#dayawardrate_divthr" + 6 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr7", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 7 + " " + "input").val("");
			$("#dayawardrate_divthr" + 7).hide();
			$("#quotainput_divthr" + 7).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 7).show();
			$("#quotainput_divthr" + 7 + " " + "input").val("");
			$("#quotainput_divthr" + 7).hide();
		} else {
			$("#quotainput_divthr" + 7).hide();
			$("#dayawardrate_divthr" + 7).hide();
			$("#quotainput_divthr" + 7 + " " + "input").val("");
			$("#dayawardrate_divthr" + 7 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr8", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 8 + " " + "input").val("");
			$("#dayawardrate_divthr" + 8).hide();
			$("#quotainput_divthr" + 8).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 8).show();
			$("#quotainput_divthr" + 8 + " " + "input").val("");
			$("#quotainput_divthr" + 8).hide();
		} else {
			$("#quotainput_divthr" + 8).hide();
			$("#dayawardrate_divthr" + 8).hide();
			$("#quotainput_divthr" + 8 + " " + "input").val("");
			$("#dayawardrate_divthr" + 8 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr9", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 9 + " " + "input").val("");
			$("#dayawardrate_divthr" + 9).hide();
			$("#quotainput_divthr" + 9).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 9).show();
			$("#quotainput_divthr" + 9 + " " + "input").val("");
			$("#quotainput_divthr" + 9).hide();
		} else {
			$("#quotainput_divthr" + 9).hide();
			$("#dayawardrate_divthr" + 9).hide();
			$("#quotainput_divthr" + 9 + " " + "input").val("");
			$("#dayawardrate_divthr" + 9 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typethr10", function() {
		if($(this).val() == "iequotathr") {
			$("#dayawardrate_divthr" + 10 + " " + "input").val("");
			$("#dayawardrate_divthr" + 10).hide();
			$("#quotainput_divthr" + 10).show();
		} else if($(this).val() == "iepercentthr") {
			$("#dayawardrate_divthr" + 10).show();
			$("#quotainput_divthr" + 10 + " " + "input").val("");
			$("#quotainput_divthr" + 10).hide();
		} else {
			$("#quotainput_divthr" + 10).hide();
			$("#dayawardrate_divthr" + 10).hide();
			$("#quotainput_divthr" + 10 + " " + "input").val("");
			$("#dayawardrate_divthr" + 10 + " " + "input").val("");
		}
	});
	//奖励方式
	$("body").on("change", "#inputplusawardtype1", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 1 + " " + "select").val("");
			$("#plusPAwardName_div" + 1).hide();
			$("#pluspenaltyname_div" + 1).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 1 + " " + "input").val("");
			$("#pluspenaltyname_div" + 1 + " " + "select").val("");
			$("#pluspenaltyname_div" + 1).hide();
			$("#plusPAwardName_div" + 1).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 1 + " " + "input").val("");
			$("#pluspenaltyname_div" + 1 + " " + "select").val("");
			$("#plusPAwardName_div" + 1 + " " + "select").val("");
			$("#pluspenaltyname_div" + 1).show();
			$("#plusPAwardName_div" + 1).show();
		} else {
			$("#pluspenaltyname_div" + 1 + " " + "input").val("");
			$("#pluspenaltyname_div" + 1 + " " + "select").val("");
			$("#plusPAwardName_div" + 1 + " " + "select").val("");
			$("#pluspenaltyname_div" + 1).hide();
			$("#plusPAwardName_div" + 1).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype2", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 2 + " " + "select").val("");
			$("#plusPAwardName_div" + 2).hide();
			$("#pluspenaltyname_div" + 2).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 2 + " " + "input").val("");
			$("#pluspenaltyname_div" + 2 + " " + "select").val("");
			$("#pluspenaltyname_div" + 2).hide();
			$("#plusPAwardName_div" + 2).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 2 + " " + "input").val("");
			$("#pluspenaltyname_div" + 2 + " " + "select").val("");
			$("#plusPAwardName_div" + 2 + " " + "select").val("");
			$("#pluspenaltyname_div" + 2).show();
			$("#plusPAwardName_div" + 2).show();
		} else {
			$("#pluspenaltyname_div" + 2 + " " + "input").val("");
			$("#pluspenaltyname_div" + 2 + " " + "select").val("");
			$("#plusPAwardName_div" + 2 + " " + "select").val("");
			$("#pluspenaltyname_div" + 2).hide();
			$("#plusPAwardName_div" + 2).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype3", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 3 + " " + "select").val("");
			$("#plusPAwardName_div" + 3).hide();
			$("#pluspenaltyname_div" + 3).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 3 + " " + "input").val("");
			$("#pluspenaltyname_div" + 3 + " " + "select").val("");
			$("#pluspenaltyname_div" + 3).hide();
			$("#plusPAwardName_div" + 3).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 3 + " " + "input").val("");
			$("#pluspenaltyname_div" + 3 + " " + "select").val("");
			$("#plusPAwardName_div" + 3 + " " + "select").val("");
			$("#pluspenaltyname_div" + 3).show();
			$("#plusPAwardName_div" + 3).show();
		} else {
			$("#pluspenaltyname_div" + 3 + " " + "input").val("");
			$("#pluspenaltyname_div" + 3 + " " + "select").val("");
			$("#plusPAwardName_div" + 3 + " " + "select").val("");
			$("#pluspenaltyname_div" + 3).hide();
			$("#plusPAwardName_div" + 3).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype4", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 4 + " " + "select").val("");
			$("#plusPAwardName_div" + 4).hide();
			$("#pluspenaltyname_div" + 4).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 4 + " " + "input").val("");
			$("#pluspenaltyname_div" + 4 + " " + "select").val("");
			$("#pluspenaltyname_div" + 4).hide();
			$("#plusPAwardName_div" + 4).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 4 + " " + "input").val("");
			$("#pluspenaltyname_div" + 4 + " " + "select").val("");
			$("#plusPAwardName_div" + 4 + " " + "select").val("");
			$("#pluspenaltyname_div" + 4).show();
			$("#plusPAwardName_div" + 4).show();
		} else {
			$("#pluspenaltyname_div" + 4 + " " + "input").val("");
			$("#pluspenaltyname_div" + 4 + " " + "select").val("");
			$("#plusPAwardName_div" + 4 + " " + "select").val("");
			$("#pluspenaltyname_div" + 4).hide();
			$("#plusPAwardName_div" + 4).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype5", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 5 + " " + "select").val("");
			$("#plusPAwardName_div" + 5).hide();
			$("#pluspenaltyname_div" + 5).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 5 + " " + "input").val("");
			$("#pluspenaltyname_div" + 5 + " " + "select").val("");
			$("#pluspenaltyname_div" + 5).hide();
			$("#plusPAwardName_div" + 5).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 5 + " " + "input").val("");
			$("#pluspenaltyname_div" + 5 + " " + "select").val("");
			$("#plusPAwardName_div" + 5 + " " + "select").val("");
			$("#pluspenaltyname_div" + 5).show();
			$("#plusPAwardName_div" + 5).show();
		} else {
			$("#pluspenaltyname_div" + 5 + " " + "input").val("");
			$("#pluspenaltyname_div" + 5 + " " + "select").val("");
			$("#plusPAwardName_div" + 5 + " " + "select").val("");
			$("#pluspenaltyname_div" + 5).hide();
			$("#plusPAwardName_div" + 5).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype6", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 6 + " " + "select").val("");
			$("#plusPAwardName_div" + 6).hide();
			$("#pluspenaltyname_div" + 6).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 6 + " " + "input").val("");
			$("#pluspenaltyname_div" + 6 + " " + "select").val("");
			$("#pluspenaltyname_div" + 6).hide();
			$("#plusPAwardName_div" + 6).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 6 + " " + "input").val("");
			$("#pluspenaltyname_div" + 6 + " " + "select").val("");
			$("#plusPAwardName_div" + 6 + " " + "select").val("");
			$("#pluspenaltyname_div" + 6).show();
			$("#plusPAwardName_div" + 6).show();
		} else {
			$("#pluspenaltyname_div" + 6 + " " + "input").val("");
			$("#pluspenaltyname_div" + 6 + " " + "select").val("");
			$("#plusPAwardName_div" + 6 + " " + "select").val("");
			$("#pluspenaltyname_div" + 6).hide();
			$("#plusPAwardName_div" + 6).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype7", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 7 + " " + "select").val("");
			$("#plusPAwardName_div" + 7).hide();
			$("#pluspenaltyname_div" + 7).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 7 + " " + "input").val("");
			$("#pluspenaltyname_div" + 7 + " " + "select").val("");
			$("#pluspenaltyname_div" + 7).hide();
			$("#plusPAwardName_div" + 7).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 7 + " " + "input").val("");
			$("#pluspenaltyname_div" + 7 + " " + "select").val("");
			$("#plusPAwardName_div" + 7 + " " + "select").val("");
			$("#pluspenaltyname_div" + 7).show();
			$("#plusPAwardName_div" + 7).show();
		} else {
			$("#pluspenaltyname_div" + 7 + " " + "input").val("");
			$("#pluspenaltyname_div" + 7 + " " + "select").val("");
			$("#plusPAwardName_div" + 7 + " " + "select").val("");
			$("#pluspenaltyname_div" + 7).hide();
			$("#plusPAwardName_div" + 7).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype8", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 8 + " " + "select").val("");
			$("#plusPAwardName_div" + 8).hide();
			$("#pluspenaltyname_div" + 8).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 8 + " " + "input").val("");
			$("#pluspenaltyname_div" + 8 + " " + "select").val("");
			$("#pluspenaltyname_div" + 8).hide();
			$("#plusPAwardName_div" + 8).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 8 + " " + "input").val("");
			$("#pluspenaltyname_div" + 8 + " " + "select").val("");
			$("#plusPAwardName_div" + 8 + " " + "select").val("");
			$("#pluspenaltyname_div" + 8).show();
			$("#plusPAwardName_div" + 8).show();
		} else {
			$("#pluspenaltyname_div" + 8 + " " + "input").val("");
			$("#pluspenaltyname_div" + 8 + " " + "select").val("");
			$("#plusPAwardName_div" + 8 + " " + "select").val("");
			$("#pluspenaltyname_div" + 8).hide();
			$("#plusPAwardName_div" + 8).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype9", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 9 + " " + "select").val("");
			$("#plusPAwardName_div" + 9).hide();
			$("#pluspenaltyname_div" + 9).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 9 + " " + "input").val("");
			$("#pluspenaltyname_div" + 9 + " " + "select").val("");
			$("#pluspenaltyname_div" + 9).hide();
			$("#plusPAwardName_div" + 9).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 9 + " " + "input").val("");
			$("#pluspenaltyname_div" + 9 + " " + "select").val("");
			$("#plusPAwardName_div" + 9 + " " + "select").val("");
			$("#pluspenaltyname_div" + 9).show();
			$("#plusPAwardName_div" + 9).show();
		} else {
			$("#pluspenaltyname_div" + 9 + " " + "input").val("");
			$("#pluspenaltyname_div" + 9 + " " + "select").val("");
			$("#plusPAwardName_div" + 9 + " " + "select").val("");
			$("#pluspenaltyname_div" + 9).hide();
			$("#plusPAwardName_div" + 9).hide();
		}
	});
	$("body").on("change", "#inputplusawardtype10", function(e) {
		if($(this).val() == 1) { //平太罚金
			$("#plusPAwardName_div" + 10 + " " + "select").val("");
			$("#plusPAwardName_div" + 10).hide();
			$("#pluspenaltyname_div" + 10).show();
		} else if($(this).val() == 2) { //平台奖励
			$("#pluspenaltyname_div" + 10 + " " + "input").val("");
			$("#pluspenaltyname_div" + 10 + " " + "select").val("");
			$("#pluspenaltyname_div" + 10).hide();
			$("#plusPAwardName_div" + 10).show();
		} else if($(this).val() == 3) { //平台罚金及平太奖励
			$("#pluspenaltyname_div" + 10 + " " + "input").val("");
			$("#pluspenaltyname_div" + 10 + " " + "select").val("");
			$("#plusPAwardName_div" + 10 + " " + "select").val("");
			$("#pluspenaltyname_div" + 10).show();
			$("#plusPAwardName_div" + 10).show();
		} else {
			$("#pluspenaltyname_div" + 10 + " " + "input").val("");
			$("#pluspenaltyname_div" + 10 + " " + "select").val("");
			$("#plusPAwardName_div" + 10 + " " + "select").val("");
			$("#pluspenaltyname_div" + 10).hide();
			$("#plusPAwardName_div" + 10).hide();
		}
	});
	//类型
	$('body').on("change", "#typetwo1", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 1 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 1).hide();
			$("#quotainput_divtwo" + 1).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 1).show();
			$("#quotainput_divtwo" + 1 + " " + "input").val("");
			$("#quotainput_divtwo" + 1).hide();
		} else {
			$("#quotainput_divtwo" + 1).hide();
			$("#dayawardrate_divtwo" + 1).hide();
			$("#quotainput_divtwo" + 1 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 1 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo2", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 2 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 2).hide();
			$("#quotainput_divtwo" + 2).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 2).show();
			$("#quotainput_divtwo" + 2 + " " + "input").val("");
			$("#quotainput_divtwo" + 2).hide();
		} else {
			$("#quotainput_divtwo" + 2).hide();
			$("#dayawardrate_divtwo" + 2).hide();
			$("#quotainput_divtwo" + 2 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 2 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo3", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 3 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 3).hide();
			$("#quotainput_divtwo" + 3).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 3).show();
			$("#quotainput_divtwo" + 3 + " " + "input").val("");
			$("#quotainput_divtwo" + 3).hide();
		} else {
			$("#quotainput_divtwo" + 3).hide();
			$("#dayawardrate_divtwo" + 3).hide();
			$("#quotainput_divtwo" + 3 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 3 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo4", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 4 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 4).hide();
			$("#quotainput_divtwo" + 4).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 4).show();
			$("#quotainput_divtwo" + 4 + " " + "input").val("");
			$("#quotainput_divtwo" + 4).hide();
		} else {
			$("#quotainput_divtwo" + 4).hide();
			$("#dayawardrate_divtwo" + 4).hide();
			$("#quotainput_divtwo" + 4 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 4 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo5", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 5 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 5).hide();
			$("#quotainput_divtwo" + 5).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 5).show();
			$("#quotainput_divtwo" + 5 + " " + "input").val("");
			$("#quotainput_divtwo" + 5).hide();
		} else {
			$("#quotainput_divtwo" + 5).hide();
			$("#dayawardrate_divtwo" + 5).hide();
			$("#quotainput_divtwo" + 5 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 5 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo6", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 6 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 6).hide();
			$("#quotainput_divtwo" + 6).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 6).show();
			$("#quotainput_divtwo" + 6 + " " + "input").val("");
			$("#quotainput_divtwo" + 6).hide();
		} else {
			$("#quotainput_divtwo" + 6).hide();
			$("#dayawardrate_divtwo" + 6).hide();
			$("#quotainput_divtwo" + 6 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 6 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo7", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 7 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 7).hide();
			$("#quotainput_divtwo" + 7).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 7).show();
			$("#quotainput_divtwo" + 7 + " " + "input").val("");
			$("#quotainput_divtwo" + 7).hide();
		} else {
			$("#quotainput_divtwo" + 7).hide();
			$("#dayawardrate_divtwo" + 7).hide();
			$("#quotainput_divtwo" + 7 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 7 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo8", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 8 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 8).hide();
			$("#quotainput_divtwo" + 8).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 8).show();
			$("#quotainput_divtwo" + 8 + " " + "input").val("");
			$("#quotainput_divtwo" + 8).hide();
		} else {
			$("#quotainput_divtwo" + 8).hide();
			$("#dayawardrate_divtwo" + 8).hide();
			$("#quotainput_divtwo" + 8 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 8 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo9", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 9 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 9).hide();
			$("#quotainput_divtwo" + 9).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 9).show();
			$("#quotainput_divtwo" + 9 + " " + "input").val("");
			$("#quotainput_divtwo" + 9).hide();
		} else {
			$("#quotainput_divtwo" + 9).hide();
			$("#dayawardrate_divtwo" + 9).hide();
			$("#quotainput_divtwo" + 9 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 9 + " " + "input").val("");
		}
	});
	$('body').on("change", "#typetwo10", function() {
		if($(this).val() == "iequotatwo") {
			$("#dayawardrate_divtwo" + 10 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 10).hide();
			$("#quotainput_divtwo" + 10).show();
		} else if($(this).val() == "iepercenttwo") {
			$("#dayawardrate_divtwo" + 10).show();
			$("#quotainput_divtwo" + 10 + " " + "input").val("");
			$("#quotainput_divtwo" + 10).hide();
		} else {
			$("#quotainput_divtwo" + 10).hide();
			$("#dayawardrate_divtwo" + 10).hide();
			$("#quotainput_divtwo" + 10 + " " + "input").val("");
			$("#dayawardrate_divtwo" + 10 + " " + "input").val("");
		}
	});
});