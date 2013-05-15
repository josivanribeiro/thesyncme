/*
	File name: rf.js
	Author: Josivan Ribeiro
	Author URI: http://www.thesyncme.com
	Description: JavaScript file that defines custom functions for adjustments in the layout of the RichFaces 4.1 JSF framework components.
	Version: 1.0
*/

/**
 * Sets a new width for an element according with its id.
 * 
 * @param id the element id.
 * @param newWidth the new width value.
 */
function setElementWidthById (id, newWidth) {
	var element = null;
	element = document.getElementById (id);
	if (element != "undefined") {
		element.style.width = newWidth;		
	}
}

/**
 * Sets a new height for an element according with its id.
 * 
 * @param id the element id.
 * @param newHeight the new width value.
 */
function setElementHeightById (id, newHeight) {
	var element = null;
	element = document.getElementById (id);
	if (element != "undefined") {
		element.style.height = newHeight;
	}
}

/**
 * Sets a new zIndex for a DIV element according its id.
 * 
 * @param divId the element id.
 * @param newZIndex the new zIndex value.
 */
function setDivZIndexById (divId, newZIndex) {
	var div = null;
	div = document.getElementById (divId);
	if (div != "undefined") {
		div.style.zIndex = newZIndex;
	}
}

/**
 * Sets the id of the DOM elements given the class name, default id and tag name.
 * 
 * @param className the class name used to find out the elements.
 * @param defaultId the default name used as id.
 * @param tag the tag name used to find out the elements.
 */
function setIdElementsByClassName (className, defaultId, tag) {
	var arr = null;
	arr = getElementsByClassName (className, tag);
	if (arr != null && arr.length > 0) {
		for (var i = 0; i < arr.length; i++) {
			var element = arr[i];
			var newElementId = defaultId + i;
			element.id = newElementId;
		}
	}
}

/**
 * Gets a DOM element by its class name and tag name.
 * 
 * @param className the class name.
 * @param tag the tag name.
 * @returns the DOM element.
 */
function getElementByClassName (className, tag) {
	var arr = null;
	var selected = null;
	var arr = document.getElementsByTagName (tag);
	if (arr != null && arr.length > 0) {
		for (var i=0; i < arr.length; i++) {
			var element = arr[i];
			if (element.className != null
					&& element.className == className) {
				selected = element;
				break;
			}
		}
	}
	return selected;
}

/**
 * Gets DOM elements by their class name and tag name.
 * 
 * @param className the class name used to find out the elements.
 * @param tag the tag name used to find out the elements.
 * @returns {Array} an array containing the DOM elements encountered.
 */
function getElementsByClassName (className, tag) {
	var arr = null;
	var selectedArr = new Array();
	arr = document.getElementsByTagName (tag);
	if (arr != null && arr.length > 0) {
		for (var i=0; i < arr.length; i++) {
			var element = arr[i];
			if (element.className != null && element.className == className) {
				selectedArr.push (element);
			}
		}
	}
	return selectedArr;
}

/**
 * Gets a given DOM element by its parent node id, class name and tag name.
 * 
 * @param parentNodeId the id of the parent node element.
 * @param className the class name.
 * @param tag the tag name. 
 * @returns the DOM element with the class name and parent node id specified.
 */
function getElementByParentNodeIdAndClassName (parentNodeId, className, tag) {
	var arr = null;
	var selected = null;
	var arr = document.getElementsByTagName (tag);
	if (arr != null && arr.length > 0) {		
		for (var i=0; i < arr.length; i++) {
			var element = arr[i];
			var parentNode = element.parentNode;
			if (parentNode != null 
					&& parentNode.id != null
					&& parentNode.id == parentNodeId
					&& element.className == className) {
				selected = element;
				break;			
			}
		}
	}
	return selected;
}

/**
 * Gets a given DOM element by its parent node class name, class name and tag name.
 * 
 * @param parentNodeClassName the parent node class name.
 * @param className the class name.
 * @param tag the tag name.
 * @returns the DOM element with the class name and parent node class name specified.
 */
function getElementByParentNodeClassNameAndClassName (parentNodeClassName, className, tag) {
	var arr = null;
	var selected = null;
	var arr = document.getElementsByTagName (tag);
	if (arr != null && arr.length > 0) {		
		for (var i=0; i < arr.length; i++) {
			var element = arr[i];
			var parentNode = element.parentNode;
			if (parentNode != null 
					&& parentNode.className != null
					&& parentNode.className == parentNodeClassName
					&& element.className == className) {
				selected = element;
				break;
			}
		}
	}
	return selected;
}

/**
 * Changes the class name attribute value of a element tag according
 * with its current class name attribute value and parent node id.
 * 
 * @param parentNodeId the id of the parent node element.
 * @param fromClassName the current class name attribute value.
 * @param toClassName the new class name attribute value.
 */
function changeElementClassNameByParentNodeId (parentNodeId, fromClassName, toClassName, tag) {
	var element = null;
	element = getElementByParentNodeIdAndClassName (parentNodeId, fromClassName, tag);
	if (element != null && element != "undefined") {
		element.className = toClassName;
	}
}

/**
 * Changes a element class name according with its parent node class name.
 * 
 * @param parentNodeClassName the parent node class name.
 * @param fromClassName the current class name which will be replaced.
 * @param toClassName the new class name used in the attribute replacement.
 * @param tag the element tag name which will be searched.
 */
function changeElementClassNameByParentNodeClassName (parentNodeClassName, fromClassName, toClassName, tag) {
	var element = null;
	element = getElementByParentNodeClassNameAndClassName (parentNodeClassName, fromClassName, tag);
	if (element != null && element != "undefined") {
		element.className = toClassName;
	}
}

/**
 * Adjusts the height for a given DIV element given its default height
 * and the additional height to be added.
 * 
 * @param elementId the DIV element id.
 * @param defaultDivHeight the default height value.
 * @param additionalHeight the additional height value.
 */
function adjustDivHeight (elementId, defaultDivHeight, additionalHeight) {
	var newHeight = null;
	var div = document.getElementById (elementId);
	if (div != "undefined"
			&& defaultDivHeight != null
			&& additionalHeight != null) {
		newHeight = parseInt(defaultDivHeight) + parseInt(additionalHeight);
		newHeight += "px";
		div.style.height = newHeight;
	}
}

/**
 * Gets the additional height based on the number of span error messages.
 * 
 * @param formId the form id.
 * @param errorClassName the error class name.
 * @returns the additional height
 */
function getAdditionalHeightBasedOnErrorMessages (formId, errorClassName) {
	var form = null;
	var numberErrorMessages = null;
	var additionalHeight = null;
	numberErrorMessages = getNumberErrorMessagesByForm (formId, errorClassName);
	switch (numberErrorMessages) {
		case 1:
			additionalHeight = 42;
			break;
		case 2:
			additionalHeight = 84;
			break;
		case 3:
			additionalHeight = 126;
			break;
		case 4:
			additionalHeight = 168;
			break;
	}
	return additionalHeight;
}

/**
 * Gets the additional height based on the number of all span error messages.
 * 
 * @param errorClassName the error class name.
 * @returns the additional height.
 */
function getAdditionalHeightBasedOnAllErrorMessages (errorClassName) {
	var numberErrorMessages = null;
	var additionalHeight = null;
	numberErrorMessages = getNumberErrorMessages (errorClassName);
	switch (numberErrorMessages) {
		case 2:
			additionalHeight = 40;
			break;
		case 3:
			additionalHeight = 76;
			break;
		case 4:
			additionalHeight = 118;
			break;
	}	
	return additionalHeight;
}

/**
 * Gets the number of span error messages from a specific form.
 * 
 * @param formId the form id.
 * @param errorClassName the error class name used to search for the span error.
 * @returns {Number} the number of span error elements.
 */
function getNumberErrorMessagesByForm (formId, errorClassName) {
	var count = 0;
	var form = document.getElementById (formId);
	if (form != "undefined") {
		var childNodeArr = form.childNodes;
		if (childNodeArr != "undefined") {
			for (var i = 0; i < childNodeArr.length; i++) {
				var node = childNodeArr [i];
				var firstChild = node.firstChild;
				if (firstChild != null 
						&& firstChild.firstChild != null
						&& firstChild.firstChild.className == errorClassName
						&& firstChild.firstChild.innerHTML != null) {
					count++;					
				}
			}
		}
	}	
	return count;
}

/**
 * Gets the number of error message spans.
 * 
 * @param errorClassName the error class name.
 * @returns {Number} the number of span error elements.
 */
function getNumberErrorMessages (errorClassName) {
	var count = 0;
	var SPAN_TAG = "span";
	var spanArr = document.getElementsByTagName (SPAN_TAG);
	if (spanArr != "undefined"
			&& spanArr.length > 0) {		
		for (var i = 0; i < spanArr.length; i++) {
			var span = spanArr [i];
			if (span.className == errorClassName) {
				count++;
			}
		}		
	}	
	return count;
}

/**
 * Adds a style sheet to the current document.
 * Inserting it before the last link element.
 * @param href the style sheet href attribute.
 */
function addStyleSheet (href) {
	// firstly, creating a new style sheet element
	var styleSheet = document.createElement("link");
	styleSheet.setAttribute("rel", "stylesheet");
	styleSheet.setAttribute("type", "text/css");
	styleSheet.setAttribute("href", href);
    // getting the current style sheet elements
	var linkArr = document.getElementsByTagName ("link");
	if (linkArr != "undefined" && linkArr.length > 0) {
    	var endIndex = parseInt (linkArr.length - 1);
    	// getting the last style sheet element
    	var lastLink = linkArr [endIndex];
    	// inserting the new styleSheet as last element
    	insertAfter (lastLink, styleSheet);
    }
}

/**
 * Inserts a new child node after an existing child node.
 * 
 * @param referenceNode the existing node.
 * @param newNode the new node.
 */
function insertAfter (referenceNode, newNode) {
    referenceNode.parentNode.insertBefore (newNode, referenceNode.nextSibling);
}

/**
 * Sets the background-image style for a specific div.
 * 
 * @param divId the div id.
 * @param filename the image file name.
 */
function setDivBackgroundImage (divId, filename) {
	var div = document.getElementById (divId);
	var IMAGES_PATH = "/thesyncme/resources/images/";
	var imagePath = IMAGES_PATH + filename;
	var url = "url('" + imagePath + "')";
	if (div != "undefined") {
		div.style.backgroundImage = url;
	}
}

