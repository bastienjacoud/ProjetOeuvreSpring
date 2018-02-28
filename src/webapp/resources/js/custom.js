function regularizePanels() {
	var list = document.getElementsByClassName("fill-div");
	var height = 0;
	[].forEach.call(list, function(element, index) {
		if(height < element.clientHeight) {
			height = element.clientHeight;
		}
	});
	[].forEach.call(list, function(element, index) {
		var old = element.clientHeight;
		console.log(old + ", " + height);
		element.style.height = height + "px";
		var foot = element.children[0].children[0].children[1];
		foot.style.position = 'relative';
		foot.style.top = (height - old) + "px";
	});
}