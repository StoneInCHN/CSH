<html style="font-size: 142.3px;">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta name="msapplication-tap-highlight" content="no">
<title>车品商城</title>

<style type="text/css">
.swiper-container {
	margin: 0 auto;
	position: relative;
	overflow: hidden;
	z-index: 1
}

.swiper-container-no-flexbox .swiper-slide {
	float: left
}

.swiper-container-vertical>.swiper-wrapper {
	-webkit-box-orient: vertical;
	-moz-box-orient: vertical;
	-ms-flex-direction: column;
	-webkit-flex-direction: column;
	flex-direction: column
}

.swiper-wrapper {
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1;
	display: -webkit-box;
	display: -moz-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-transition-property: -webkit-transform;
	-moz-transition-property: -moz-transform;
	-o-transition-property: -o-transform;
	-ms-transition-property: -ms-transform;
	transition-property: transform;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box
}

.swiper-container-android .swiper-slide, .swiper-wrapper {
	-webkit-transform: translate3d(0, 0, 0);
	-moz-transform: translate3d(0, 0, 0);
	-o-transform: translate(0, 0);
	-ms-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0)
}

.swiper-container-multirow>.swiper-wrapper {
	-webkit-box-lines: multiple;
	-moz-box-lines: multiple;
	-ms-flex-wrap: wrap;
	-webkit-flex-wrap: wrap;
	flex-wrap: wrap
}

.swiper-container-free-mode>.swiper-wrapper {
	-webkit-transition-timing-function: ease-out;
	-moz-transition-timing-function: ease-out;
	-ms-transition-timing-function: ease-out;
	-o-transition-timing-function: ease-out;
	transition-timing-function: ease-out;
	margin: 0 auto
}

.swiper-slide {
	-webkit-flex-shrink: 0;
	-ms-flex: 0 0 auto;
	flex-shrink: 0;
	width: 100%;
	height: 100%;
	position: relative
}

.swiper-container-autoheight, .swiper-container-autoheight .swiper-slide
	{
	height: auto
}

.swiper-container-autoheight .swiper-wrapper {
	-webkit-box-align: start;
	-ms-flex-align: start;
	-webkit-align-items: flex-start;
	align-items: flex-start;
	-webkit-transition-property: -webkit-transform, height;
	-moz-transition-property: -moz-transform;
	-o-transition-property: -o-transform;
	-ms-transition-property: -ms-transform;
	transition-property: transform, height
}

.swiper-container .swiper-notification {
	position: absolute;
	left: 0;
	top: 0;
	pointer-events: none;
	opacity: 0;
	z-index: -1000
}

.swiper-wp8-horizontal {
	-ms-touch-action: pan-y;
	touch-action: pan-y
}

.swiper-wp8-vertical {
	-ms-touch-action: pan-x;
	touch-action: pan-x
}

.swiper-button-next, .swiper-button-prev {
	position: absolute;
	top: 50%;
	width: 27px;
	height: 44px;
	margin-top: -22px;
	z-index: 10;
	cursor: pointer;
	-moz-background-size: 27px 44px;
	-webkit-background-size: 27px 44px;
	background-size: 27px 44px;
	background-position: center;
	background-repeat: no-repeat
}

.swiper-button-next.swiper-button-disabled, .swiper-button-prev.swiper-button-disabled
	{
	opacity: .35;
	cursor: auto;
	pointer-events: none
}

.swiper-button-prev, .swiper-container-rtl .swiper-button-next {
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23007aff'%2F%3E%3C%2Fsvg%3E");
	left: 10px;
	right: auto
}

.swiper-button-prev.swiper-button-black, .swiper-container-rtl .swiper-button-next.swiper-button-black
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23000000'%2F%3E%3C%2Fsvg%3E")
}

.swiper-button-prev.swiper-button-white, .swiper-container-rtl .swiper-button-next.swiper-button-white
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23ffffff'%2F%3E%3C%2Fsvg%3E")
}

.swiper-button-next, .swiper-container-rtl .swiper-button-prev {
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23007aff'%2F%3E%3C%2Fsvg%3E");
	right: 10px;
	left: auto
}

.swiper-button-next.swiper-button-black, .swiper-container-rtl .swiper-button-prev.swiper-button-black
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23000000'%2F%3E%3C%2Fsvg%3E")
}

.swiper-button-next.swiper-button-white, .swiper-container-rtl .swiper-button-prev.swiper-button-white
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23ffffff'%2F%3E%3C%2Fsvg%3E")
}

.swiper-pagination {
	position: absolute;
	text-align: center;
	-webkit-transition: .3s;
	-moz-transition: .3s;
	-o-transition: .3s;
	transition: .3s;
	-webkit-transform: translate3d(0, 0, 0);
	-ms-transform: translate3d(0, 0, 0);
	-o-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
	z-index: 10
}

.swiper-pagination.swiper-pagination-hidden {
	opacity: 0
}

.swiper-container-horizontal>.swiper-pagination-bullets,
	.swiper-pagination-custom, .swiper-pagination-fraction {
	bottom: 10px;
	left: 0;
	width: 100%
}

.swiper-pagination-bullet {
	width: 8px;
	height: 8px;
	display: inline-block;
	border-radius: 100%;
	background: #000;
	opacity: .2
}

button.swiper-pagination-bullet {
	border: 0;
	margin: 0;
	padding: 0;
	box-shadow: none;
	-moz-appearance: none;
	-ms-appearance: none;
	-webkit-appearance: none;
	appearance: none
}

.swiper-pagination-clickable .swiper-pagination-bullet {
	cursor: pointer
}

.swiper-pagination-white .swiper-pagination-bullet {
	background: #fff
}

.swiper-pagination-bullet-active {
	opacity: 1;
	background: #007aff
}

.swiper-pagination-white .swiper-pagination-bullet-active {
	background: #fff
}

.swiper-pagination-black .swiper-pagination-bullet-active {
	background: #000
}

.swiper-container-vertical>.swiper-pagination-bullets {
	right: 10px;
	top: 50%;
	-webkit-transform: translate3d(0, -50%, 0);
	-moz-transform: translate3d(0, -50%, 0);
	-o-transform: translate(0, -50%);
	-ms-transform: translate3d(0, -50%, 0);
	transform: translate3d(0, -50%, 0)
}

.swiper-container-vertical>.swiper-pagination-bullets .swiper-pagination-bullet
	{
	margin: 5px 0;
	display: block
}

.swiper-container-horizontal>.swiper-pagination-bullets .swiper-pagination-bullet
	{
	margin: 0 5px
}

.swiper-pagination-progress {
	background: rgba(0, 0, 0, .25);
	position: absolute
}

.swiper-pagination-progress .swiper-pagination-progressbar {
	background: #007aff;
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	-webkit-transform: scale(0);
	-ms-transform: scale(0);
	-o-transform: scale(0);
	transform: scale(0);
	-webkit-transform-origin: left top;
	-moz-transform-origin: left top;
	-ms-transform-origin: left top;
	-o-transform-origin: left top;
	transform-origin: left top
}

.swiper-container-rtl .swiper-pagination-progress .swiper-pagination-progressbar
	{
	-webkit-transform-origin: right top;
	-moz-transform-origin: right top;
	-ms-transform-origin: right top;
	-o-transform-origin: right top;
	transform-origin: right top
}

.swiper-container-horizontal>.swiper-pagination-progress {
	width: 100%;
	height: 4px;
	left: 0;
	top: 0
}

.swiper-container-vertical>.swiper-pagination-progress {
	width: 4px;
	height: 100%;
	left: 0;
	top: 0
}

.swiper-pagination-progress.swiper-pagination-white {
	background: rgba(255, 255, 255, .5)
}

.swiper-pagination-progress.swiper-pagination-white .swiper-pagination-progressbar
	{
	background: #fff
}

.swiper-pagination-progress.swiper-pagination-black .swiper-pagination-progressbar
	{
	background: #000
}

.swiper-container-3d {
	-webkit-perspective: 1200px;
	-moz-perspective: 1200px;
	-o-perspective: 1200px;
	perspective: 1200px
}

.swiper-container-3d .swiper-cube-shadow, .swiper-container-3d .swiper-slide,
	.swiper-container-3d .swiper-slide-shadow-bottom, .swiper-container-3d .swiper-slide-shadow-left,
	.swiper-container-3d .swiper-slide-shadow-right, .swiper-container-3d .swiper-slide-shadow-top,
	.swiper-container-3d .swiper-wrapper {
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	-ms-transform-style: preserve-3d;
	transform-style: preserve-3d
}

.swiper-container-3d .swiper-slide-shadow-bottom, .swiper-container-3d .swiper-slide-shadow-left,
	.swiper-container-3d .swiper-slide-shadow-right, .swiper-container-3d .swiper-slide-shadow-top
	{
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	pointer-events: none;
	z-index: 10
}

.swiper-container-3d .swiper-slide-shadow-left {
	background-image: -webkit-gradient(linear, left top, right top, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-3d .swiper-slide-shadow-right {
	background-image: -webkit-gradient(linear, right top, left top, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-3d .swiper-slide-shadow-top {
	background-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-3d .swiper-slide-shadow-bottom {
	background-image: -webkit-gradient(linear, left bottom, left top, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-coverflow .swiper-wrapper, .swiper-container-flip .swiper-wrapper
	{
	-ms-perspective: 1200px
}

.swiper-container-cube, .swiper-container-flip {
	overflow: visible
}

.swiper-container-cube .swiper-slide, .swiper-container-flip .swiper-slide
	{
	pointer-events: none;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-ms-backface-visibility: hidden;
	backface-visibility: hidden;
	z-index: 1
}

.swiper-container-cube .swiper-slide .swiper-slide,
	.swiper-container-flip .swiper-slide .swiper-slide {
	pointer-events: none
}

.swiper-container-cube .swiper-slide-active, .swiper-container-cube .swiper-slide-active .swiper-slide-active,
	.swiper-container-flip .swiper-slide-active, .swiper-container-flip .swiper-slide-active .swiper-slide-active
	{
	pointer-events: auto
}

.swiper-container-cube .swiper-slide-shadow-bottom,
	.swiper-container-cube .swiper-slide-shadow-left,
	.swiper-container-cube .swiper-slide-shadow-right,
	.swiper-container-cube .swiper-slide-shadow-top, .swiper-container-flip .swiper-slide-shadow-bottom,
	.swiper-container-flip .swiper-slide-shadow-left,
	.swiper-container-flip .swiper-slide-shadow-right,
	.swiper-container-flip .swiper-slide-shadow-top {
	z-index: 0;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-ms-backface-visibility: hidden;
	backface-visibility: hidden
}

.swiper-container-cube .swiper-slide {
	visibility: hidden;
	-webkit-transform-origin: 0 0;
	-moz-transform-origin: 0 0;
	-ms-transform-origin: 0 0;
	transform-origin: 0 0;
	width: 100%;
	height: 100%
}

.swiper-container-cube.swiper-container-rtl .swiper-slide {
	-webkit-transform-origin: 100% 0;
	-moz-transform-origin: 100% 0;
	-ms-transform-origin: 100% 0;
	transform-origin: 100% 0
}

.swiper-container-cube .swiper-slide-active, .swiper-container-cube .swiper-slide-next,
	.swiper-container-cube .swiper-slide-next+.swiper-slide,
	.swiper-container-cube .swiper-slide-prev {
	pointer-events: auto;
	visibility: visible
}

.swiper-container-cube .swiper-cube-shadow {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .6;
	-webkit-filter: blur(50px);
	filter: blur(50px);
	z-index: 0
}

.swiper-container-fade.swiper-container-free-mode .swiper-slide {
	-webkit-transition-timing-function: ease-out;
	-moz-transition-timing-function: ease-out;
	-ms-transition-timing-function: ease-out;
	-o-transition-timing-function: ease-out;
	transition-timing-function: ease-out
}

.swiper-container-fade .swiper-slide {
	pointer-events: none;
	-webkit-transition-property: opacity;
	-moz-transition-property: opacity;
	-o-transition-property: opacity;
	transition-property: opacity
}

.swiper-container-fade .swiper-slide .swiper-slide {
	pointer-events: none
}

.swiper-container-fade .swiper-slide-active, .swiper-container-fade .swiper-slide-active .swiper-slide-active
	{
	pointer-events: auto
}

.swiper-scrollbar {
	border-radius: 10px;
	position: relative;
	-ms-touch-action: none;
	background: rgba(0, 0, 0, .1)
}

.swiper-container-horizontal>.swiper-scrollbar {
	position: absolute;
	left: 1%;
	bottom: 3px;
	z-index: 50;
	height: 5px;
	width: 98%
}

.swiper-container-vertical>.swiper-scrollbar {
	position: absolute;
	right: 3px;
	top: 1%;
	z-index: 50;
	width: 5px;
	height: 98%
}

.swiper-scrollbar-drag {
	height: 100%;
	width: 100%;
	position: relative;
	background: rgba(0, 0, 0, .5);
	border-radius: 10px;
	left: 0;
	top: 0
}

.swiper-scrollbar-cursor-drag {
	cursor: move
}

.swiper-lazy-preloader {
	width: 42px;
	height: 42px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -21px;
	margin-top: -21px;
	z-index: 10;
	-webkit-transform-origin: 50%;
	-moz-transform-origin: 50%;
	transform-origin: 50%;
	-webkit-animation: swiper-preloader-spin 1s steps(12, end) infinite;
	-moz-animation: swiper-preloader-spin 1s steps(12, end) infinite;
	animation: swiper-preloader-spin 1s steps(12, end) infinite
}

.swiper-lazy-preloader:after {
	display: block;
	content: "";
	width: 100%;
	height: 100%;
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20viewBox%3D'0%200%20120%20120'%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20xmlns%3Axlink%3D'http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink'%3E%3Cdefs%3E%3Cline%20id%3D'l'%20x1%3D'60'%20x2%3D'60'%20y1%3D'7'%20y2%3D'27'%20stroke%3D'%236c6c6c'%20stroke-width%3D'11'%20stroke-linecap%3D'round'%2F%3E%3C%2Fdefs%3E%3Cg%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(30%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(60%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(90%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(120%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(150%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.37'%20transform%3D'rotate(180%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.46'%20transform%3D'rotate(210%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.56'%20transform%3D'rotate(240%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.66'%20transform%3D'rotate(270%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.75'%20transform%3D'rotate(300%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.85'%20transform%3D'rotate(330%2060%2C60)'%2F%3E%3C%2Fg%3E%3C%2Fsvg%3E");
	background-position: 50%;
	-webkit-background-size: 100%;
	background-size: 100%;
	background-repeat: no-repeat
}

.swiper-lazy-preloader-white:after {
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20viewBox%3D'0%200%20120%20120'%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20xmlns%3Axlink%3D'http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink'%3E%3Cdefs%3E%3Cline%20id%3D'l'%20x1%3D'60'%20x2%3D'60'%20y1%3D'7'%20y2%3D'27'%20stroke%3D'%23fff'%20stroke-width%3D'11'%20stroke-linecap%3D'round'%2F%3E%3C%2Fdefs%3E%3Cg%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(30%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(60%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(90%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(120%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(150%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.37'%20transform%3D'rotate(180%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.46'%20transform%3D'rotate(210%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.56'%20transform%3D'rotate(240%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.66'%20transform%3D'rotate(270%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.75'%20transform%3D'rotate(300%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.85'%20transform%3D'rotate(330%2060%2C60)'%2F%3E%3C%2Fg%3E%3C%2Fsvg%3E")
}

@
-webkit-keyframes swiper-preloader-spin { 100%{
	-webkit-transform: rotate(360deg)
}

}
@
keyframes swiper-preloader-spin { 100%{
	transform: rotate(360deg)
}
}
</style>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0)
}

body {
	background: #efeff4
}

body, button, input, select, textarea {
	font: 12px/1.5 hiragino sans gb, microsoft yahei, simsun, sans-serif
}

img {
	border: 0 0
}

ul, li {
	list-style: none
}

a {
	text-decoration: none
}

em, i, h1, h2, h3, h4, h5 {
	font-style: normal;
	font-weight: 400
}

.clear {
	clear: both;
	overflow: hidden
}

.top {
	width: 100%;
	height: 1.093rem;
	text-align: center;
	z-index: 100;
	position: relative;
	background: #efeff4
}

.top a {
	display: block
}

.top ul {
	margin-left: .35rem
}

.top ul li {
	width: 33.333%;
	height: 1.093rem;
	line-height: 1.093rem;
	float: left;
	text-align: center;
	color: #797979;
	font-size: .4rem
}

.top .hanghuo {
	display: inline-block;
	background: url(../../resources/image/1.png)
		no-repeat 5% center;
	background-size: 19%
}

.top .invoice {
	display: inline-block;
	background: url(../../resources/image/3.png)
		no-repeat 5% center;
	background-size: 19%
}

.top .baoyou {
	display: inline-block;
	background: url(../../resources/image/2.png)
		no-repeat 5% center;
	background-size: 19%
}


.close {
	background-image:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAYAAACohjseAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo5MDVmYzQxMy04MjFmLTQ3NDAtYTk0NS1lMDM0ODkyMzkwMmIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MTE2Q0QzOTRFQ0I4MTFFNUI0RENGMDVDNzc1QzNFODQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MTE2Q0QzOTNFQ0I4MTFFNUI0RENGMDVDNzc1QzNFODQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDozMWI4MWQzOC1iZDZjLTQzYzgtYmZmZS02NjkxYjg4OTA0NmMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6OTA1ZmM0MTMtODIxZi00NzQwLWE5NDUtZTAzNDg5MjM5MDJiIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+nsmRwAAABRxJREFUeNrkm0tIVUEYx+eesJeXXosicJEkVrsIFMFAAtEWYheEBC03LhTcCSFUrgQhhHaCLoQoExQCAxcmQhouJEHa+cCIQIja9MDbw57/T/9Hh+O53nPunXPuqQb+DOdy78z3m5kzM983c2PKcDpTXLofWRl0UR6hc1ABFIfy+bUktAatQgvQEjQDzS4tP/9q0p6YIaiDyBLQdagCOuDyte+EUoTNc/mOwE1BD6BRwH7OKSDATiLrgJpptA0yCz2FXkDL0AqM/eb47T5kRVAxdB66xJ63waUxBqA7+O2bUAFh3GFkXVArDfoFTUL32fLJDMvN50hogiohiw3WB3Wi3I+BA8KIRmR3oeOsfBDqRuUrht9l6d1bUCMb8R3UjnoeBgKICuNsyUZ+9ARqQ4UvVYAJ9Z5G1gtV8yMBbEW9a8YAWckYdBb6ALWgghEVYoIN9WzgI9AiVOOlcWMeCpYJYBw6Ac1B9Sj4lcpBgi2FyIahEugtdBm2vMgYkHDT0CHoMdRgYuo2sCQNQVegT7Is7QZppRmW44STiaQu13CSaEMdbRLbxmmr9x7khDLHd+4x4X6qCCXYuAfZI/akvJMlbhNPqh7sI9wch2Wk4NiTYlOD1hF9nnqQ69wgZ8sLuZpQfE4885xdrznXyZjLDmWZi3h92EtBFpBXObvKZqBY3/E4h2gX4Z78LXAcriPceBwnw853kBvnVm6/2ny2YCyAXvFbZhttbyHLjh7s4J5v0M/2C4XVInvG4W0K7jDLrPXRiy85d+wlyzYgF89megXdPlv5Bp3bCROQLGOCZd7w2ZPdZGgm01YPJujPTfrxCvDd37InhJ5DpdlCanClLLOGdXi1Z4VuW5xMW4DXmd/P4AWXGasqW0gXuKpM/D+NYYMpxhjKe0h2BkezcFYzNtAgnO00C88P6JjFMMF+BnySmQ6vTHvSJBztSDJkInGhMosvs2IMJdv1yBekaTgt2SwXLYb2FANEKizIAOF0ljMW45aKWzQVBmTAcBsmMD8ngAV8MBo0SgUZApzOUmBxzfjujFsGBRkCnNS7zm1bXABlWl1TASUXyEDhtCRM+Zb6x5MAJtV22D0IX835zhnZ1nlIwpS02JV5PCsIGq7KxLbOQ7176RmtCeAqPy8KGk7eOVN71zTJZlkVwAU+FAcNZ3qDvpsJzBcsbVE8HwZcSJA2y5IAzvDhUlhwIUDaLDMWd95yslpGVyMUuKAgySAe0hdhs3gmPsVZJxG2LxgAZIIs08JmL/QPmDflyhM3CNmkM9mAo1wPK3my6ifoNGbQWXVCjvkJOtH2SrKMbgHyxGaAzzd9Bp16OFEZ2VtqkFJmj5+gE20XhgH7JCym0Uuw9LXtR/mMjcZ8GmK8TB6hyZouvzll38ywtJaTD/r5gvb6bHWjcBmW2Uvb+/VrJ05v4rbaPMCo5oHGX5FoazVt73R6E87x387Hfh5NRR2ukCNPUrtzHrBchoacr4nkvG3YDoFHFE5sG6atD93u0KRyeOWUaeNYGBricXHU4MSmIdq4SJuVJ0CedcuZg1zVkDPwe1GCpC33aJvYWJPqYtD/e42EPSk/rNB6ciqXEw/rntJ6riLdRaC0QScWUK69k/O5WEJY57z2zpWng0s7RB0V/LuX8RwVhnmdUvaW11QY1yldXCS5zdCiNs/Eg7wQu86FPJwLsQ6DvFxplpjPCsPpztBeEQNE0brSnGLqNnEp/QuXpWhcSk8BG6m/FfwRYABcB9pXktFH3QAAAABJRU5ErkJggg==");
	width: .746rem;
	height: .746rem;
	position: absolute;
	bottom: .5333rem;
	left: 46.2666%;
	margin: 0 auto;
	background-repeat: no-repeat;
	background-size: 100%
}

.fadein {
	-webkit-animation: fadein 1s forwards
}

.fadeout {
	-webkit-animation: fadeout 1s forwards
}

@
-webkit-keyframes fadein {
	from {opacity: 0
}

to {
	opacity: 1
}

}
@
-webkit-keyframes fadeout {
	from {opacity: 1
}

to {
	opacity: 0
}

}
.banner {
	width: 100%;
	height: 2.016666rem;
	overflow: hidden;
	margin-bottom: .266rem;
	z-index: 100;
	position: relative
}

.banner .swiper-container {
	height: 2.016666rem
}

.banner img {
	width: 100%;
	height: 2.016666rem
}



.fr {
	width: 6rem;
	float: right;
	margin-top: .4rem
}

.navtop {
	
}

.nav {
	position: relative;
	top: 0;
	width: 100%;
	height: 1.0666rem;
	background-color: #fff;
	border-bottom: #e2e2e2 1px solid
}

.navcontent {
	background: #fff;
	width: 100%;
	height: 1.0666rem;
	border-bottom: #e2e2e2 1px solid
}

.m-nav {
	position: relative
}

.nav .slidewrap {
	width: 86.6%;
	overflow: hidden;
	height: 1.0666rem;
	line-height: 1.0666rem;
	overflow-x: auto;
	border-bottom: #e2e2e2 1px solid;
	margin: 0;
	z-index: 30
}

.slidewrap::-webkit-scrollbar {
	display: none
}

.nav .slidewrap ul {
	width: 100%;
	height: 1.0666rem;
	line-height: 1.0666rem;
	white-space: nowrap
}

.nav .slidewrap ul li {
	display: inline-block;
	width: 2.16rem;
	height: 1.0666rem;
	line-height: 1.0666rem;
	overflow: hidden;
	position: relative;
	text-align: center;
	font-size: .4rem;
	color: #535353;
	background: #fff;
	border-bottom: #e2e2e2 1px solid
}

.nav .slidewrap ul li a {
	height: 1.01rem;
	display: block;
	color: #797979;
	line-height: 1rem
}

.nav .dropdown {
	width: 13.4%;
	position: absolute;
	z-index: 40;
	top: 0;
	right: 0;
	height: 1.0666rem;
	line-height: 1.0666rem;
	text-align: center;
	box-shadow: -.1rem 0 10px #e4e4e4;
	background: #fff;
	-webkit-box-shadow: -.1rem 0 10px #e4e4e4;
	border-bottom: #e2e2e2 1px solid
}

.jiantou {
	-webkit-animation: jiantou .5s forwards
}

.jiantou2 {
	-webkit-animation: jiantou2 .5s forwards
}

@
-webkit-keyframes jiantou { 0%{
	transform: rotate(0deg)
}

100%{
transform


:rotate(180deg)


}
}
@
-webkit-keyframes jiantou2 { 0%{
	transform: rotate(180deg)
}

100%{
transform


:rotate(0deg)


}
}
.nav .downlist {
	width: 100%;
	opacity: 0;
	position: absolute;
	background: rgba(255, 255, 255, .9);
	box-shadow: 0 2px 8px #e4e4e4;
	z-index: 41;
	top: 1.09rem;
	-webkit-transition: all .7s ease;
	-webkit-transform: translateY(-4rem) translateZ(0)
}

.nav .fromtop {
	opacity: 1;
	-webkit-transform: translateY(0rem) translateZ(0)
}

.nav .downlist-hide {
	display: none
}

.nav .downlist ul li {
	display: inline-block;
	width: 24.1%;
	height: 1.1rem;
	line-height: 1.1rem;
	overflow: hidden;
	position: relative;
	text-align: center;
	font-size: .346rem;
	color: #666
}

.nav .downlist ul li a {
	color: #666;
	display: block
}

.nav .slidewrap .current {
	color: #f22550;
	border-bottom: #f22550 2px solid
}

.nav .downlist .current {
	color: #f22550
}

#wrapper {
	position: absolute;
	z-index: 1;
	top: 420px;
	bottom: 0;
	left: 0;
	width: 100%;
	background: #fff
}

.snav {
	width: 100%;
	overflow: hidden;
	background: #fff;
	z-index: 1
}

.snavwrap {
	overflow: hidden;
	background: #fff;
	margin: .25333rem 0 0rem 3.3333%
}

.snavwrap li {
	display: inline-block;
	width: 20.266%;
	height: .88rem;
	line-height: .88rem;
	text-align: center;
	margin-right: 3.4666%;
	font-size: .34666rem;
	color: #797979;
	margin-bottom: .25333rem;
	border: #e4e4e4 1px solid;
	border-radius: 5px
}

.snavwrap li a {
	display: block;
	color: #797979;
	border: #e4e4e4 1px solid;
	border-radius: 5px
}

.snavwrap .current {
	border: #f22550 1px solid;
	color: #f22550
}

.g-car {
	margin: .2666rem 0;
	background: #fff;
	width: 100%;
	height: 1.17333rem;
	position: relative;
	z-index: 19
}

.g-car .m-cartt {
	width: 68.333%;
	height: 1.17333rem;
	line-height: 1.17333rem;
	margin-left: 2.6666%;
	color: #797979;
	font-size: .37333rem
}

.g-car .addcarpic {
	width: .537777rem;
	height: .50666rem;
	vertical-align: middle;
	padding-right: .22666rem;
	position: absolute;
	left: .333rem;
	top: .36rem
}

.g-car .nocar1 {
	width: 218px;
	margin-left: .8rem;
	display: inline-block;
	font-size: 14px
}

.g-car .unload img {
	width: .74666rem;
	height: .74666rem;
	vertical-align: middle;
	padding-right: .22666rem
}

.g-car .nocar img {
	width: .537777rem;
	height: .50666rem;
	vertical-align: middle;
	padding-right: .22666rem
}

.g-car .hascar img {
	width: .69333rem;
	height: .69333rem;
	vertical-align: middle;
	padding-right: .22666rem
}

.g-car .m-handle {
	width: 26%;
	height: 1.17333rem;
	line-height: 1.17333rem;
	position: absolute;
	right: .333rem;
	top: 0;
	color: #a9a9a9;
	font-size: .34rem;
	text-align: right
}

.g-car .m-handle a {
	display: block;
	color: #a9a9a9
}

.g-car .m-handle img {
	width: .18rem;
	padding-left: .16rem
}



.g-main {
	width: 100%;
	overflow: hidden;
	margin-top: .08rem
}

.g-mainc {
	float: left;
	width: 49.6%;
	overflow: hidden;
	background: #fff;
	border-bottom: #d7d7d7 1px solid;
	margin-bottom: .08rem;
	padding-bottom: .06rem;
	position: relative
}

.g-mainc:nth-child(odd) {
	margin-right: .8%
}

.g-main .m-content {
	width: 86.9565%;
	height: 4.44rem;
	padding: .32rem;
	overflow: hidden
}

.g-main .m-content img {
	width: 100%;
	height: 4.33rem
}

.g-main .m-goods {
	width: 89%;
	margin: 0 auto
}

.g-main .m-goods .u-goodsname {
	overflow: hidden;
	height: .96rem;
	line-height: .5rem;
	color: #666;
	font-size: .3733333rem;
	display: -webkit-box;
	text-overflow: ellipsis;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	width: 100%
}

.g-main .m-goods .u-price {
	color: #999
}

.g-main .m-goods .price {
	float: left;
	height: .76rem;
	line-height: .76rem;
	color: #e62d46;
	font-size: .32rem;
	font-weight: 700
}

.g-main .m-goods .price em {
	height: .76rem;
	line-height: .76rem;
	color: #e62d46;
	font-size: .64rem
}

.g-main .m-goods .del {
	font-size: .3rem;
	padding-left: .1rem
}

.g-main .m-goods .hassold {
	float: right;
	display: inline-block;
	text-align: right;
	font-size: .32rem;
	padding-top: .19rem
}

.g-main .label {
	display: block;
	position: absolute;
	top: 0;
	right: 0;
	width: .666666rem;
	height: .56rem;
	background-image:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAqCAYAAADxughHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NThDN0U3MzIwNTNFMTFFNkIzOUNFNzE5MDNDQTAzQTIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NThDN0U3MzMwNTNFMTFFNkIzOUNFNzE5MDNDQTAzQTIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo1OEM3RTczMDA1M0UxMUU2QjM5Q0U3MTkwM0NBMDNBMiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo1OEM3RTczMTA1M0UxMUU2QjM5Q0U3MTkwM0NBMDNBMiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PhhK6NAAAARNSURBVHja7FhpSFRRFD7PNzpTllhaGkgb/rEFW7QgKspsJYsIwoj6VyEUEhESJCJGCyb+CYLoRyVWf9rLwmwhqx/ZJqFBRVEYmUuL2aQz8+Z1zp37nrO89xxnKZN34XDfvLnv3vOd+53lXqE7M0uG/78JMTBEmgnEBGICMYGYQP5Js+hmmJSxAMlJoWeo0aPAXf+IPYt5q0BISwtbWdex4wMHIubmgHXP7rAWtm/aAnLTK7DkLAbL4kWGY+WODo8BkpMjC0Tu6mK94+w5cDe/CrQ4Wti6fSs4r14D6XGD9hxNnu8ch8qhd9cedXdsZaXQU1zC+l8zsn1YIH9pC5gntmg3xG3MD41aSiMQ0tWagPdxZSWsdx49prm4D6B+/h/ouJCAKJYaVn3K9x2ngP97Bu5WHTgPV2h/P3w466x7izw/6254du38RUPqRAQIWYoUdzU8Affbt4ZjtSigfn/3Hsjd3RCbtxpct++wngAQRaMWtbSa9Ox5v1Yz4rL0+g0A+R4CIL9igHA+BYiej4QXfhMSApVcvw4sC+aHZzqcV4lQAU69o4CBi+yOcCDy9+99jt/Wjhb7YjzjlAzDv8VZM9k8mhRG2imRMsCwI0aERy3567e+OF7/oF9qWV406AeNhJFgyc5iiioGitcY7x0oIkCtkZ6Hjk4fawpFoSfJ2LVrQLbbwXWyivnC752FICQmsnwSNWePSU1li7KIQ06IvBYnTWTiHYJpDJD4ZWit5rx8BeSWT6pDKyUMRBOIkJICcnuHGj7tuSvBUrANpAuXVEWIFqQcUUGYmgHiwgWG1CPjSF0/2Tx/rWiMmTgBpKbmPlphaUFh0oGU0+JwbP4GNeLogRHnzgFx+rSQFLVWlrNeKXWCAsKqVczAlDeU+G4t3Mloo+eIjuJSENDiBFZuadEsaxxV1Zog4w0ChL8hBnQeoWqVJbD79Z78sb+UlRY9pWWGCzn2lYD7w0dWfhDVInpwmjxZpXpQO0LWJ+QSFotUvdrOnAYRc0NveQULxUbZl973HqkA2+GDYDuwH36vXe9LD9ytYMoRopG7tbUPRHo6xEwYzyrt4H2EDlMYhZw8KUmNjeC6XgOu6nPMSf0VkdF5fapljEREITV8e58nsNZiZYq/4nxO17UaEDMzmSEtvLhka1DIxjqPKm3d4KR1ZUq0UM4SAbuFBy416+v4gtZ31sojzDha48kn3e/ea64Z7IFUMO9+zVsUE4gJxAQyqG4aw2hulKcoN1HoioRuK6jmWYqyAiUtGkAilUfo7FrLFa/lv/VaBge0DGUh3QhFAkeoQFwoj7jVSRr5Tgy02TiYXA5uejhA6Cw7OojBLV6K16H8iAJDxqEs57IEZUwQ33xFSSIfqUIp1BjQi/LQS/mXf8FnP6Oc5EKBaDb3LQI2T8enTyg+Eoc9Hb82o3R6Oek9FPsgCkxUTudwChIVk/gm0JHRQUCGRPj9I8AA7Xa3bdf/A6UAAAAASUVORK5CYII=");
	background-size: 100%
}

@font-face {
	font-family: iconfont;
	src: url(${base}/resources/fonts/iconfont.eot?t=1458732982#iefix)
		format('embedded-opentype'),
		url(${base}/resources/fonts/iconfont.woff?t=1458732982)
		format('woff'),
		url(${base}/resources/fonts/iconfont.ttf?t=1458732982)
		format('truetype'),
		url(${base}/resources/fonts/iconfont.svg?t=1458732982#iconfont)
		format('svg')
}

.iconfont {
	font-family: iconfont !important;
	font-size: .45rem;
	font-style: normal;
	-webkit-font-smoothing: antialiased;
	-webkit-text-stroke-width: .2px;
	-moz-osx-font-smoothing: grayscale
}

.icon-zhankai:before {
	content: "\e63d"
}

.icon-unfold:before {
	content: "\e640"
}

#nav {
	position: absolute;
	top: 0;
	bottom: 48px;
	left: 0;
	overflow: hidden;
	background: #fff
}

#scroller {
	position: absolute;
	background: #fff;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	width: auto;
	-webkit-transform: translateZ(0);
	-moz-transform: translateZ(0);
	-ms-transform: translateZ(0);
	-o-transform: translateZ(0);
	transform: translateZ(0);
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-webkit-text-size-adjust: none;
	-moz-text-size-adjust: none;
	-ms-text-size-adjust: none;
	-o-text-size-adjust: none;
	text-size-adjust: none
}

.swiper-container .swiper-pagination {
	bottom: .1rem
}

.snavfix {
	position: fixed;
	top: 1.077777777rem;
	z-index: 20;
	width: 100%;
	background: #efeff4;
	-webkit-transform: translateZ(0px)
}

.nogoods {
	text-align: center;
	margin-top: .3rem
}

.nogoodspic {
	width: 2.13333rem;
	height: 2.106666rem;
	background-image:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAACeCAYAAAC1vwHwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA4ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo1NDU5ZmZiNC1lOWQ0LTZjNGMtOWYyMy1iY2MzOTY4MTBjMDgiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REEyMTQ0N0UxQkQ3MTFFNkIyNEVCQUM4RThGNDMxODYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REEyMTQ0N0QxQkQ3MTFFNkIyNEVCQUM4RThGNDMxODYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4MzA3Njk4NC1kODlhLTRiYTItOWU4MS1hNTE4YjliMjZmOGIiIHN0UmVmOmRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDpmMDg0YjQ0Ni02MzkyLTExNzgtYWVh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");
	background-size: 100%;
	margin: 0 auto;
	background-repeat: no-repeat
}

.nogoodstxt {
	display: block;
	color: #666;
	font-size: .373333rem;
	margin-top: .2rem
}

section {
	width: 100%;
	height: 100%;
	background: #FFF;
	text-align: center;
	font-size: .5rem;
	z-index: 99999;
	position: absolute;
	top: 0;
	left: 0
}

section p {
	width: 10rem;
	text-align: center;
	position: absolute;
	top: 48%;
	margin: 0 auto
}

section p span {
	display: inline-block;
	font-size: .35rem;
	border: #ededed 1px solid;
	border-radius: 5px;
	margin-top: .2rem;
	padding: .2rem;
	color: #999
}
</style>
<style type="text/css">
.dropload-up, .dropload-down {
	width: 100%;
	position: relative;
	height: 0;
	overflow: hidden;
	font-size: 12px;
	-webkit-transform: translateZ(0);
	transform: translateZ(0)
}

.dropload-down {
	width: 100%;
	height: 50px
}

.dropload-refresh, .dropload-update, .dropload-load, .dropload-noData {
	height: 50px;
	line-height: 50px;
	text-align: center
}

.dropload-load .loading {
	display: inline-block;
	height: 15px;
	width: 15px;
	border-radius: 100%;
	margin: 6px;
	border: 2px solid #1576e3;
	border-bottom-color: transparent;
	vertical-align: middle;
	-webkit-animation: rotate .75s linear infinite;
	animation: rotate .75s linear infinite
}

@
-webkit-keyframes rotate { 0%{
	-webkit-transform: rotate(0deg)
}

50%{
-webkit-transform


:rotate(180deg)


}
100%{
-webkit-transform


:rotate(360deg)


}
}
@
keyframes rotate { 0%{
	transform: rotate(0deg)
}
50%{
transform


:rotate(180deg)


}
100%{
transform


:rotate(360deg)


}
}
</style>
<script type="text/javascript">var Zepto=function(){function t(t){return null==t?String(t):Y[J.call(t)]||"object"}function e(e){return"function"==t(e)}function n(t){return null!=t&&t==t.window}function i(t){return null!=t&&t.nodeType==t.DOCUMENT_NODE}function r(e){return"object"==t(e)}function o(t){return r(t)&&!n(t)&&Object.getPrototypeOf(t)==Object.prototype}function a(t){return"number"==typeof t.length}function s(t){return M.call(t,function(t){return null!=t})}function u(t){return t.length>0?j.fn.concat.apply([],t):t}function c(t){return t.replace(/::/g,"/").replace(/([A-Z]+)([A-Z][a-z])/g,"$1_$2").replace(/([a-z\d])([A-Z])/g,"$1_$2").replace(/_/g,"-").toLowerCase()}function l(t){return t in A?A[t]:A[t]=new RegExp("(^|\\s)"+t+"(\\s|$)")}function f(t,e){return"number"!=typeof e||$[c(t)]?e:e+"px"}function h(t){var e,n;return Z[t]||(e=L.createElement(t),L.body.appendChild(e),n=getComputedStyle(e,"").getPropertyValue("display"),e.parentNode.removeChild(e),"none"==n&&(n="block"),Z[t]=n),Z[t]}function p(t){return"children"in t?D.call(t.children):j.map(t.childNodes,function(t){return 1==t.nodeType?t:void 0})}function d(t,e){var n,i=t?t.length:0;for(n=0;i>n;n++)this[n]=t[n];this.length=i,this.selector=e||""}function m(t,e,n){for(T in e)n&&(o(e[T])||Q(e[T]))?(o(e[T])&&!o(t[T])&&(t[T]={}),Q(e[T])&&!Q(t[T])&&(t[T]=[]),m(t[T],e[T],n)):e[T]!==E&&(t[T]=e[T])}function v(t,e){return null==e?j(t):j(t).filter(e)}function g(t,n,i,r){return e(n)?n.call(t,i,r):n}function y(t,e,n){null==n?t.removeAttribute(e):t.setAttribute(e,n)}function x(t,e){var n=t.className||"",i=n&&n.baseVal!==E;return e===E?i?n.baseVal:n:void(i?n.baseVal=e:t.className=e)}function w(t){try{return t?"true"==t||("false"==t?!1:"null"==t?null:+t+""==t?+t:/^[\[\{]/.test(t)?j.parseJSON(t):t):t}catch(e){return t}}function b(t,e){e(t);for(var n=0,i=t.childNodes.length;i>n;n++)b(t.childNodes[n],e)}var E,T,j,C,S,N,O=[],P=O.concat,M=O.filter,D=O.slice,L=window.document,Z={},A={},$={"column-count":1,columns:1,"font-weight":1,"line-height":1,opacity:1,"z-index":1,zoom:1},k=/^\s*<(\w+|!)[^>]*>/,R=/^<(\w+)\s*\/?>(?:<\/\1>|)$/,z=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,q=/^(?:body|html)$/i,F=/([A-Z])/g,_=["val","css","html","text","data","width","height","offset"],I=["after","prepend","before","append"],H=L.createElement("table"),U=L.createElement("tr"),X={tr:L.createElement("tbody"),tbody:H,thead:H,tfoot:H,td:U,th:U,"*":L.createElement("div")},V=/complete|loaded|interactive/,B=/^[\w-]*$/,Y={},J=Y.toString,W={},G=L.createElement("div"),K={tabindex:"tabIndex",readonly:"readOnly","for":"htmlFor","class":"className",maxlength:"maxLength",cellspacing:"cellSpacing",cellpadding:"cellPadding",rowspan:"rowSpan",colspan:"colSpan",usemap:"useMap",frameborder:"frameBorder",contenteditable:"contentEditable"},Q=Array.isArray||function(t){return t instanceof Array};return W.matches=function(t,e){if(!e||!t||1!==t.nodeType)return!1;var n=t.webkitMatchesSelector||t.mozMatchesSelector||t.oMatchesSelector||t.matchesSelector;if(n)return n.call(t,e);var i,r=t.parentNode,o=!r;return o&&(r=G).appendChild(t),i=~W.qsa(r,e).indexOf(t),o&&G.removeChild(t),i},S=function(t){return t.replace(/-+(.)?/g,function(t,e){return e?e.toUpperCase():""})},N=function(t){return M.call(t,function(e,n){return t.indexOf(e)==n})},W.fragment=function(t,e,n){var i,r,a;return R.test(t)&&(i=j(L.createElement(RegExp.$1))),i||(t.replace&&(t=t.replace(z,"<$1></$2>")),e===E&&(e=k.test(t)&&RegExp.$1),e in X||(e="*"),a=X[e],a.innerHTML=""+t,i=j.each(D.call(a.childNodes),function(){a.removeChild(this)})),o(n)&&(r=j(i),j.each(n,function(t,e){_.indexOf(t)>-1?r[t](e):r.attr(t,e)})),i},W.Z=function(t,e){return new d(t,e)},W.isZ=function(t){return t instanceof W.Z},W.init=function(t,n){var i;if(!t)return W.Z();if("string"==typeof t)if(t=t.trim(),"<"==t[0]&&k.test(t))i=W.fragment(t,RegExp.$1,n),t=null;else{if(n!==E)return j(n).find(t);i=W.qsa(L,t)}else{if(e(t))return j(L).ready(t);if(W.isZ(t))return t;if(Q(t))i=s(t);else if(r(t))i=[t],t=null;else if(k.test(t))i=W.fragment(t.trim(),RegExp.$1,n),t=null;else{if(n!==E)return j(n).find(t);i=W.qsa(L,t)}}return W.Z(i,t)},j=function(t,e){return W.init(t,e)},j.extend=function(t){var e,n=D.call(arguments,1);return"boolean"==typeof t&&(e=t,t=n.shift()),n.forEach(function(n){m(t,n,e)}),t},W.qsa=function(t,e){var n,i="#"==e[0],r=!i&&"."==e[0],o=i||r?e.slice(1):e,a=B.test(o);return t.getElementById&&a&&i?(n=t.getElementById(o))?[n]:[]:1!==t.nodeType&&9!==t.nodeType&&11!==t.nodeType?[]:D.call(a&&!i&&t.getElementsByClassName?r?t.getElementsByClassName(o):t.getElementsByTagName(e):t.querySelectorAll(e))},j.contains=L.documentElement.contains?function(t,e){return t!==e&&t.contains(e)}:function(t,e){for(;e&&(e=e.parentNode);)if(e===t)return!0;return!1},j.type=t,j.isFunction=e,j.isWindow=n,j.isArray=Q,j.isPlainObject=o,j.isEmptyObject=function(t){var e;for(e in t)return!1;return!0},j.inArray=function(t,e,n){return O.indexOf.call(e,t,n)},j.camelCase=S,j.trim=function(t){return null==t?"":String.prototype.trim.call(t)},j.uuid=0,j.support={},j.expr={},j.noop=function(){},j.map=function(t,e){var n,i,r,o=[];if(a(t))for(i=0;i<t.length;i++)n=e(t[i],i),null!=n&&o.push(n);else for(r in t)n=e(t[r],r),null!=n&&o.push(n);return u(o)},j.each=function(t,e){var n,i;if(a(t)){for(n=0;n<t.length;n++)if(e.call(t[n],n,t[n])===!1)return t}else for(i in t)if(e.call(t[i],i,t[i])===!1)return t;return t},j.grep=function(t,e){return M.call(t,e)},window.JSON&&(j.parseJSON=JSON.parse),j.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),function(t,e){Y["[object "+e+"]"]=e.toLowerCase()}),j.fn={constructor:W.Z,length:0,forEach:O.forEach,reduce:O.reduce,push:O.push,sort:O.sort,splice:O.splice,indexOf:O.indexOf,concat:function(){var t,e,n=[];for(t=0;t<arguments.length;t++)e=arguments[t],n[t]=W.isZ(e)?e.toArray():e;return P.apply(W.isZ(this)?this.toArray():this,n)},map:function(t){return j(j.map(this,function(e,n){return t.call(e,n,e)}))},slice:function(){return j(D.apply(this,arguments))},ready:function(t){return V.test(L.readyState)&&L.body?t(j):L.addEventListener("DOMContentLoaded",function(){t(j)},!1),this},get:function(t){return t===E?D.call(this):this[t>=0?t:t+this.length]},toArray:function(){return this.get()},size:function(){return this.length},remove:function(){return this.each(function(){null!=this.parentNode&&this.parentNode.removeChild(this)})},each:function(t){return O.every.call(this,function(e,n){return t.call(e,n,e)!==!1}),this},filter:function(t){return e(t)?this.not(this.not(t)):j(M.call(this,function(e){return W.matches(e,t)}))},add:function(t,e){return j(N(this.concat(j(t,e))))},is:function(t){return this.length>0&&W.matches(this[0],t)},not:function(t){var n=[];if(e(t)&&t.call!==E)this.each(function(e){t.call(this,e)||n.push(this)});else{var i="string"==typeof t?this.filter(t):a(t)&&e(t.item)?D.call(t):j(t);this.forEach(function(t){i.indexOf(t)<0&&n.push(t)})}return j(n)},has:function(t){return this.filter(function(){return r(t)?j.contains(this,t):j(this).find(t).size()})},eq:function(t){return-1===t?this.slice(t):this.slice(t,+t+1)},first:function(){var t=this[0];return t&&!r(t)?t:j(t)},last:function(){var t=this[this.length-1];return t&&!r(t)?t:j(t)},find:function(t){var e,n=this;return e=t?"object"==typeof t?j(t).filter(function(){var t=this;return O.some.call(n,function(e){return j.contains(e,t)})}):1==this.length?j(W.qsa(this[0],t)):this.map(function(){return W.qsa(this,t)}):j()},closest:function(t,e){var n=this[0],r=!1;for("object"==typeof t&&(r=j(t));n&&!(r?r.indexOf(n)>=0:W.matches(n,t));)n=n!==e&&!i(n)&&n.parentNode;return j(n)},parents:function(t){for(var e=[],n=this;n.length>0;)n=j.map(n,function(t){return(t=t.parentNode)&&!i(t)&&e.indexOf(t)<0?(e.push(t),t):void 0});return v(e,t)},parent:function(t){return v(N(this.pluck("parentNode")),t)},children:function(t){return v(this.map(function(){return p(this)}),t)},contents:function(){return this.map(function(){return this.contentDocument||D.call(this.childNodes)})},siblings:function(t){return v(this.map(function(t,e){return M.call(p(e.parentNode),function(t){return t!==e})}),t)},empty:function(){return this.each(function(){this.innerHTML=""})},pluck:function(t){return j.map(this,function(e){return e[t]})},show:function(){return this.each(function(){"none"==this.style.display&&(this.style.display=""),"none"==getComputedStyle(this,"").getPropertyValue("display")&&(this.style.display=h(this.nodeName))})},replaceWith:function(t){return this.before(t).remove()},wrap:function(t){var n=e(t);if(this[0]&&!n)var i=j(t).get(0),r=i.parentNode||this.length>1;return this.each(function(e){j(this).wrapAll(n?t.call(this,e):r?i.cloneNode(!0):i)})},wrapAll:function(t){if(this[0]){j(this[0]).before(t=j(t));for(var e;(e=t.children()).length;)t=e.first();j(t).append(this)}return this},wrapInner:function(t){var n=e(t);return this.each(function(e){var i=j(this),r=i.contents(),o=n?t.call(this,e):t;r.length?r.wrapAll(o):i.append(o)})},unwrap:function(){return this.parent().each(function(){j(this).replaceWith(j(this).children())}),this},clone:function(){return this.map(function(){return this.cloneNode(!0)})},hide:function(){return this.css("display","none")},toggle:function(t){return this.each(function(){var e=j(this);(t===E?"none"==e.css("display"):t)?e.show():e.hide()})},prev:function(t){return j(this.pluck("previousElementSibling")).filter(t||"*")},next:function(t){return j(this.pluck("nextElementSibling")).filter(t||"*")},html:function(t){return 0 in arguments?this.each(function(e){var n=this.innerHTML;j(this).empty().append(g(this,t,e,n))}):0 in this?this[0].innerHTML:null},text:function(t){return 0 in arguments?this.each(function(e){var n=g(this,t,e,this.textContent);this.textContent=null==n?"":""+n}):0 in this?this.pluck("textContent").join(""):null},attr:function(t,e){var n;return"string"!=typeof t||1 in arguments?this.each(function(n){if(1===this.nodeType)if(r(t))for(T in t)y(this,T,t[T]);else y(this,t,g(this,e,n,this.getAttribute(t)))}):this.length&&1===this[0].nodeType?!(n=this[0].getAttribute(t))&&t in this[0]?this[0][t]:n:E},removeAttr:function(t){return this.each(function(){1===this.nodeType&&t.split(" ").forEach(function(t){y(this,t)},this)})},prop:function(t,e){return t=K[t]||t,1 in arguments?this.each(function(n){this[t]=g(this,e,n,this[t])}):this[0]&&this[0][t]},data:function(t,e){var n="data-"+t.replace(F,"-$1").toLowerCase(),i=1 in arguments?this.attr(n,e):this.attr(n);return null!==i?w(i):E},val:function(t){return 0 in arguments?this.each(function(e){this.value=g(this,t,e,this.value)}):this[0]&&(this[0].multiple?j(this[0]).find("option").filter(function(){return this.selected}).pluck("value"):this[0].value)},offset:function(t){if(t)return this.each(function(e){var n=j(this),i=g(this,t,e,n.offset()),r=n.offsetParent().offset(),o={top:i.top-r.top,left:i.left-r.left};"static"==n.css("position")&&(o.position="relative"),n.css(o)});if(!this.length)return null;if(!j.contains(L.documentElement,this[0]))return{top:0,left:0};var e=this[0].getBoundingClientRect();return{left:e.left+window.pageXOffset,top:e.top+window.pageYOffset,width:Math.round(e.width),height:Math.round(e.height)}},css:function(e,n){if(arguments.length<2){var i,r=this[0];if(!r)return;if(i=getComputedStyle(r,""),"string"==typeof e)return r.style[S(e)]||i.getPropertyValue(e);if(Q(e)){var o={};return j.each(e,function(t,e){o[e]=r.style[S(e)]||i.getPropertyValue(e)}),o}}var a="";if("string"==t(e))n||0===n?a=c(e)+":"+f(e,n):this.each(function(){this.style.removeProperty(c(e))});else for(T in e)e[T]||0===e[T]?a+=c(T)+":"+f(T,e[T])+";":this.each(function(){this.style.removeProperty(c(T))});return this.each(function(){this.style.cssText+=";"+a})},index:function(t){return t?this.indexOf(j(t)[0]):this.parent().children().indexOf(this[0])},hasClass:function(t){return t?O.some.call(this,function(t){return this.test(x(t))},l(t)):!1},addClass:function(t){return t?this.each(function(e){if("className"in this){C=[];var n=x(this),i=g(this,t,e,n);i.split(/\s+/g).forEach(function(t){j(this).hasClass(t)||C.push(t)},this),C.length&&x(this,n+(n?" ":"")+C.join(" "))}}):this},removeClass:function(t){return this.each(function(e){if("className"in this){if(t===E)return x(this,"");C=x(this),g(this,t,e,C).split(/\s+/g).forEach(function(t){C=C.replace(l(t)," ")}),x(this,C.trim())}})},toggleClass:function(t,e){return t?this.each(function(n){var i=j(this),r=g(this,t,n,x(this));r.split(/\s+/g).forEach(function(t){(e===E?!i.hasClass(t):e)?i.addClass(t):i.removeClass(t)})}):this},scrollTop:function(t){if(this.length){var e="scrollTop"in this[0];return t===E?e?this[0].scrollTop:this[0].pageYOffset:this.each(e?function(){this.scrollTop=t}:function(){this.scrollTo(this.scrollX,t)})}},scrollLeft:function(t){if(this.length){var e="scrollLeft"in this[0];return t===E?e?this[0].scrollLeft:this[0].pageXOffset:this.each(e?function(){this.scrollLeft=t}:function(){this.scrollTo(t,this.scrollY)})}},position:function(){if(this.length){var t=this[0],e=this.offsetParent(),n=this.offset(),i=q.test(e[0].nodeName)?{top:0,left:0}:e.offset();return n.top-=parseFloat(j(t).css("margin-top"))||0,n.left-=parseFloat(j(t).css("margin-left"))||0,i.top+=parseFloat(j(e[0]).css("border-top-width"))||0,i.left+=parseFloat(j(e[0]).css("border-left-width"))||0,{top:n.top-i.top,left:n.left-i.left}}},offsetParent:function(){return this.map(function(){for(var t=this.offsetParent||L.body;t&&!q.test(t.nodeName)&&"static"==j(t).css("position");)t=t.offsetParent;return t})}},j.fn.detach=j.fn.remove,["width","height"].forEach(function(t){var e=t.replace(/./,function(t){return t[0].toUpperCase()});j.fn[t]=function(r){var o,a=this[0];return r===E?n(a)?a["inner"+e]:i(a)?a.documentElement["scroll"+e]:(o=this.offset())&&o[t]:this.each(function(e){a=j(this),a.css(t,g(this,r,e,a[t]()))})}}),I.forEach(function(e,n){var i=n%2;j.fn[e]=function(){var e,r,o=j.map(arguments,function(n){return e=t(n),"object"==e||"array"==e||null==n?n:W.fragment(n)}),a=this.length>1;return o.length<1?this:this.each(function(t,e){r=i?e:e.parentNode,e=0==n?e.nextSibling:1==n?e.firstChild:2==n?e:null;var s=j.contains(L.documentElement,r);o.forEach(function(t){if(a)t=t.cloneNode(!0);else if(!r)return j(t).remove();r.insertBefore(t,e),s&&b(t,function(t){null==t.nodeName||"SCRIPT"!==t.nodeName.toUpperCase()||t.type&&"text/javascript"!==t.type||t.src||window.eval.call(window,t.innerHTML)})})})},j.fn[i?e+"To":"insert"+(n?"Before":"After")]=function(t){return j(t)[e](this),this}}),W.Z.prototype=d.prototype=j.fn,W.uniq=N,W.deserializeValue=w,j.zepto=W,j}();window.Zepto=Zepto,void 0===window.$&&(window.$=Zepto),function(t){function e(t){return t._zid||(t._zid=h++)}function n(t,n,o,a){if(n=i(n),n.ns)var s=r(n.ns);return(v[e(t)]||[]).filter(function(t){return!(!t||n.e&&t.e!=n.e||n.ns&&!s.test(t.ns)||o&&e(t.fn)!==e(o)||a&&t.sel!=a)})}function i(t){var e=(""+t).split(".");return{e:e[0],ns:e.slice(1).sort().join(" ")}}function r(t){return new RegExp("(?:^| )"+t.replace(" "," .* ?")+"(?: |$)")}function o(t,e){return t.del&&!y&&t.e in x||!!e}function a(t){return w[t]||y&&x[t]||t}function s(n,r,s,u,l,h,p){var d=e(n),m=v[d]||(v[d]=[]);r.split(/\s/).forEach(function(e){if("ready"==e)return t(document).ready(s);var r=i(e);r.fn=s,r.sel=l,r.e in w&&(s=function(e){var n=e.relatedTarget;return!n||n!==this&&!t.contains(this,n)?r.fn.apply(this,arguments):void 0}),r.del=h;var d=h||s;r.proxy=function(t){if(t=c(t),!t.isImmediatePropagationStopped()){t.data=u;var e=d.apply(n,t._args==f?[t]:[t].concat(t._args));return e===!1&&(t.preventDefault(),t.stopPropagation()),e}},r.i=m.length,m.push(r),"addEventListener"in n&&n.addEventListener(a(r.e),r.proxy,o(r,p))})}function u(t,i,r,s,u){var c=e(t);(i||"").split(/\s/).forEach(function(e){n(t,e,r,s).forEach(function(e){delete v[c][e.i],"removeEventListener"in t&&t.removeEventListener(a(e.e),e.proxy,o(e,u))})})}function c(e,n){return(n||!e.isDefaultPrevented)&&(n||(n=e),t.each(j,function(t,i){var r=n[t];e[t]=function(){return this[i]=b,r&&r.apply(n,arguments)},e[i]=E}),(n.defaultPrevented!==f?n.defaultPrevented:"returnValue"in n?n.returnValue===!1:n.getPreventDefault&&n.getPreventDefault())&&(e.isDefaultPrevented=b)),e}function l(t){var e,n={originalEvent:t};for(e in t)T.test(e)||t[e]===f||(n[e]=t[e]);return c(n,t)}var f,h=1,p=Array.prototype.slice,d=t.isFunction,m=function(t){return"string"==typeof t},v={},g={},y="onfocusin"in window,x={focus:"focusin",blur:"focusout"},w={mouseenter:"mouseover",mouseleave:"mouseout"};g.click=g.mousedown=g.mouseup=g.mousemove="MouseEvents",t.event={add:s,remove:u},t.proxy=function(n,i){var r=2 in arguments&&p.call(arguments,2);if(d(n)){var o=function(){return n.apply(i,r?r.concat(p.call(arguments)):arguments)};return o._zid=e(n),o}if(m(i))return r?(r.unshift(n[i],n),t.proxy.apply(null,r)):t.proxy(n[i],n);throw new TypeError("expected function")},t.fn.bind=function(t,e,n){return this.on(t,e,n)},t.fn.unbind=function(t,e){return this.off(t,e)},t.fn.one=function(t,e,n,i){return this.on(t,e,n,i,1)};var b=function(){return!0},E=function(){return!1},T=/^([A-Z]|returnValue$|layer[XY]$)/,j={preventDefault:"isDefaultPrevented",stopImmediatePropagation:"isImmediatePropagationStopped",stopPropagation:"isPropagationStopped"};t.fn.delegate=function(t,e,n){return this.on(e,t,n)},t.fn.undelegate=function(t,e,n){return this.off(e,t,n)},t.fn.live=function(e,n){return t(document.body).delegate(this.selector,e,n),this},t.fn.die=function(e,n){return t(document.body).undelegate(this.selector,e,n),this},t.fn.on=function(e,n,i,r,o){var a,c,h=this;return e&&!m(e)?(t.each(e,function(t,e){h.on(t,n,i,e,o)}),h):(m(n)||d(r)||r===!1||(r=i,i=n,n=f),(r===f||i===!1)&&(r=i,i=f),r===!1&&(r=E),h.each(function(f,h){o&&(a=function(t){return u(h,t.type,r),r.apply(this,arguments)}),n&&(c=function(e){var i,o=t(e.target).closest(n,h).get(0);return o&&o!==h?(i=t.extend(l(e),{currentTarget:o,liveFired:h}),(a||r).apply(o,[i].concat(p.call(arguments,1)))):void 0}),s(h,e,r,i,n,c||a)}))},t.fn.off=function(e,n,i){var r=this;return e&&!m(e)?(t.each(e,function(t,e){r.off(t,n,e)}),r):(m(n)||d(i)||i===!1||(i=n,n=f),i===!1&&(i=E),r.each(function(){u(this,e,i,n)}))},t.fn.trigger=function(e,n){return e=m(e)||t.isPlainObject(e)?t.Event(e):c(e),e._args=n,this.each(function(){e.type in x&&"function"==typeof this[e.type]?this[e.type]():"dispatchEvent"in this?this.dispatchEvent(e):t(this).triggerHandler(e,n)})},t.fn.triggerHandler=function(e,i){var r,o;return this.each(function(a,s){r=l(m(e)?t.Event(e):e),r._args=i,r.target=s,t.each(n(s,e.type||e),function(t,e){return o=e.proxy(r),r.isImmediatePropagationStopped()?!1:void 0})}),o},"focusin focusout focus blur load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error".split(" ").forEach(function(e){t.fn[e]=function(t){return 0 in arguments?this.bind(e,t):this.trigger(e)}}),t.Event=function(t,e){m(t)||(e=t,t=e.type);var n=document.createEvent(g[t]||"Events"),i=!0;if(e)for(var r in e)"bubbles"==r?i=!!e[r]:n[r]=e[r];return n.initEvent(t,i,!0),c(n)}}(Zepto),function(t){function e(e,n,i){var r=t.Event(n);return t(e).trigger(r,i),!r.isDefaultPrevented()}function n(t,n,i,r){return t.global?e(n||y,i,r):void 0}function i(e){e.global&&0===t.active++&&n(e,null,"ajaxStart")}function r(e){e.global&&!--t.active&&n(e,null,"ajaxStop")}function o(t,e){var i=e.context;return e.beforeSend.call(i,t,e)===!1||n(e,i,"ajaxBeforeSend",[t,e])===!1?!1:void n(e,i,"ajaxSend",[t,e])}function a(t,e,i,r){var o=i.context,a="success";i.success.call(o,t,a,e),r&&r.resolveWith(o,[t,a,e]),n(i,o,"ajaxSuccess",[e,i,t]),u(a,e,i)}function s(t,e,i,r,o){var a=r.context;r.error.call(a,i,e,t),o&&o.rejectWith(a,[i,e,t]),n(r,a,"ajaxError",[i,r,t||e]),u(e,i,r)}function u(t,e,i){var o=i.context;i.complete.call(o,e,t),n(i,o,"ajaxComplete",[e,i]),r(i)}function c(){}function l(t){return t&&(t=t.split(";",2)[0]),t&&(t==T?"html":t==E?"json":w.test(t)?"script":b.test(t)&&"xml")||"text"}function f(t,e){return""==e?t:(t+"&"+e).replace(/[&?]{1,2}/,"?")}function h(e){e.processData&&e.data&&"string"!=t.type(e.data)&&(e.data=t.param(e.data,e.traditional)),!e.data||e.type&&"GET"!=e.type.toUpperCase()||(e.url=f(e.url,e.data),e.data=void 0)}function p(e,n,i,r){return t.isFunction(n)&&(r=i,i=n,n=void 0),t.isFunction(i)||(r=i,i=void 0),{url:e,data:n,success:i,dataType:r}}function d(e,n,i,r){var o,a=t.isArray(n),s=t.isPlainObject(n);t.each(n,function(n,u){o=t.type(u),r&&(n=i?r:r+"["+(s||"object"==o||"array"==o?n:"")+"]"),!r&&a?e.add(u.name,u.value):"array"==o||!i&&"object"==o?d(e,u,i,n):e.add(n,u)})}var m,v,g=0,y=window.document,x=/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,w=/^(?:text|application)\/javascript/i,b=/^(?:text|application)\/xml/i,E="application/json",T="text/html",j=/^\s*$/,C=y.createElement("a");C.href=window.location.href,t.active=0,t.ajaxJSONP=function(e,n){if(!("type"in e))return t.ajax(e);var i,r,u=e.jsonpCallback,c=(t.isFunction(u)?u():u)||"jsonp"+ ++g,l=y.createElement("script"),f=window[c],h=function(e){t(l).triggerHandler("error",e||"abort")},p={abort:h};return n&&n.promise(p),t(l).on("load error",function(o,u){clearTimeout(r),t(l).off().remove(),"error"!=o.type&&i?a(i[0],p,e,n):s(null,u||"error",p,e,n),window[c]=f,i&&t.isFunction(f)&&f(i[0]),f=i=void 0}),o(p,e)===!1?(h("abort"),p):(window[c]=function(){i=arguments},l.src=e.url.replace(/\?(.+)=\?/,"?$1="+c),y.head.appendChild(l),e.timeout>0&&(r=setTimeout(function(){h("timeout")},e.timeout)),p)},t.ajaxSettings={type:"GET",beforeSend:c,success:c,error:c,complete:c,context:null,global:!0,xhr:function(){return new window.XMLHttpRequest},accepts:{script:"text/javascript, application/javascript, application/x-javascript",json:E,xml:"application/xml, text/xml",html:T,text:"text/plain"},crossDomain:!1,timeout:0,processData:!0,cache:!0},t.ajax=function(e){var n,r,u=t.extend({},e||{}),p=t.Deferred&&t.Deferred();for(m in t.ajaxSettings)void 0===u[m]&&(u[m]=t.ajaxSettings[m]);i(u),u.crossDomain||(n=y.createElement("a"),n.href=u.url,n.href=n.href,u.crossDomain=C.protocol+"//"+C.host!=n.protocol+"//"+n.host),u.url||(u.url=window.location.toString()),(r=u.url.indexOf("#"))>-1&&(u.url=u.url.slice(0,r)),h(u);var d=u.dataType,g=/\?.+=\?/.test(u.url);if(g&&(d="jsonp"),u.cache!==!1&&(e&&e.cache===!0||"script"!=d&&"jsonp"!=d)||(u.url=f(u.url,"_="+Date.now())),"jsonp"==d)return g||(u.url=f(u.url,u.jsonp?u.jsonp+"=?":u.jsonp===!1?"":"callback=?")),t.ajaxJSONP(u,p);var x,w=u.accepts[d],b={},E=function(t,e){b[t.toLowerCase()]=[t,e]},T=/^([\w-]+:)\/\//.test(u.url)?RegExp.$1:window.location.protocol,S=u.xhr(),N=S.setRequestHeader;if(p&&p.promise(S),u.crossDomain||E("X-Requested-With","XMLHttpRequest"),E("Accept",w||"*/*"),(w=u.mimeType||w)&&(w.indexOf(",")>-1&&(w=w.split(",",2)[0]),S.overrideMimeType&&S.overrideMimeType(w)),(u.contentType||u.contentType!==!1&&u.data&&"GET"!=u.type.toUpperCase())&&E("Content-Type",u.contentType||"application/x-www-form-urlencoded"),u.headers)for(v in u.headers)E(v,u.headers[v]);if(S.setRequestHeader=E,S.onreadystatechange=function(){if(4==S.readyState){S.onreadystatechange=c,clearTimeout(x);var e,n=!1;if(S.status>=200&&S.status<300||304==S.status||0==S.status&&"file:"==T){if(d=d||l(u.mimeType||S.getResponseHeader("content-type")),"arraybuffer"==S.responseType||"blob"==S.responseType)e=S.response;else{e=S.responseText;try{"script"==d?(1,eval)(e):"xml"==d?e=S.responseXML:"json"==d&&(e=j.test(e)?null:t.parseJSON(e))}catch(i){n=i}if(n)return s(n,"parsererror",S,u,p)}a(e,S,u,p)}else s(S.statusText||null,S.status?"error":"abort",S,u,p)}},o(S,u)===!1)return S.abort(),s(null,"abort",S,u,p),S;if(u.xhrFields)for(v in u.xhrFields)S[v]=u.xhrFields[v];var O="async"in u?u.async:!0;S.open(u.type,u.url,O,u.username,u.password);for(v in b)N.apply(S,b[v]);return u.timeout>0&&(x=setTimeout(function(){S.onreadystatechange=c,S.abort(),s(null,"timeout",S,u,p)},u.timeout)),S.send(u.data?u.data:null),S},t.get=function(){return t.ajax(p.apply(null,arguments))},t.post=function(){var e=p.apply(null,arguments);return e.type="POST",t.ajax(e)},t.getJSON=function(){var e=p.apply(null,arguments);return e.dataType="json",t.ajax(e)},t.fn.load=function(e,n,i){if(!this.length)return this;var r,o=this,a=e.split(/\s/),s=p(e,n,i),u=s.success;return a.length>1&&(s.url=a[0],r=a[1]),s.success=function(e){o.html(r?t("<div>").html(e.replace(x,"")).find(r):e),u&&u.apply(o,arguments)},t.ajax(s),this};var S=encodeURIComponent;t.param=function(e,n){var i=[];return i.add=function(e,n){t.isFunction(n)&&(n=n()),null==n&&(n=""),this.push(S(e)+"="+S(n))},d(i,e,n),i.join("&").replace(/%20/g,"+")}}(Zepto),function(t){t.fn.serializeArray=function(){var e,n,i=[],r=function(t){return t.forEach?t.forEach(r):void i.push({name:e,value:t})};return this[0]&&t.each(this[0].elements,function(i,o){n=o.type,e=o.name,e&&"fieldset"!=o.nodeName.toLowerCase()&&!o.disabled&&"submit"!=n&&"reset"!=n&&"button"!=n&&"file"!=n&&("radio"!=n&&"checkbox"!=n||o.checked)&&r(t(o).val())}),i},t.fn.serialize=function(){var t=[];return this.serializeArray().forEach(function(e){t.push(encodeURIComponent(e.name)+"="+encodeURIComponent(e.value))}),t.join("&")},t.fn.submit=function(e){if(0 in arguments)this.bind("submit",e);else if(this.length){var n=t.Event("submit");this.eq(0).trigger(n),n.isDefaultPrevented()||this.get(0).submit()}return this}}(Zepto),function(){try{getComputedStyle(void 0)}catch(t){var e=getComputedStyle;window.getComputedStyle=function(t){try{return e(t)}catch(n){return null}}}}(),function(t,e){function n(t){return t.replace(/([a-z])([A-Z])/,"$1-$2").toLowerCase()}function i(t){return r?r+t:t.toLowerCase()}var r,o,a,s,u,c,l,f,h,p,d="",m={Webkit:"webkit",Moz:"",O:"o"},v=document.createElement("div"),g=/^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,y={};t.each(m,function(t,n){return v.style[t+"TransitionProperty"]!==e?(d="-"+t.toLowerCase()+"-",r=n,!1):void 0}),o=d+"transform",y[a=d+"transition-property"]=y[s=d+"transition-duration"]=y[c=d+"transition-delay"]=y[u=d+"transition-timing-function"]=y[l=d+"animation-name"]=y[f=d+"animation-duration"]=y[p=d+"animation-delay"]=y[h=d+"animation-timing-function"]="",t.fx={off:r===e&&v.style.transitionProperty===e,speeds:{_default:400,fast:200,slow:600},cssPrefix:d,transitionEnd:i("TransitionEnd"),animationEnd:i("AnimationEnd")},t.fn.animate=function(n,i,r,o,a){return t.isFunction(i)&&(o=i,r=e,i=e),t.isFunction(r)&&(o=r,r=e),t.isPlainObject(i)&&(r=i.easing,o=i.complete,a=i.delay,i=i.duration),i&&(i=("number"==typeof i?i:t.fx.speeds[i]||t.fx.speeds._default)/1e3),a&&(a=parseFloat(a)/1e3),this.anim(n,i,r,o,a)},t.fn.anim=function(i,r,d,m,v){var x,w,b,E={},T="",j=this,C=t.fx.transitionEnd,S=!1;if(r===e&&(r=t.fx.speeds._default/1e3),v===e&&(v=0),t.fx.off&&(r=0),"string"==typeof i)E[l]=i,E[f]=r+"s",E[p]=v+"s",E[h]=d||"linear",C=t.fx.animationEnd;else{w=[];for(x in i)g.test(x)?T+=x+"("+i[x]+") ":(E[x]=i[x],w.push(n(x)));T&&(E[o]=T,w.push(o)),r>0&&"object"==typeof i&&(E[a]=w.join(", "),E[s]=r+"s",E[c]=v+"s",E[u]=d||"linear")}return b=function(e){if("undefined"!=typeof e){if(e.target!==e.currentTarget)return;t(e.target).unbind(C,b)}else t(this).unbind(C,b);S=!0,t(this).css(y),m&&m.call(this)},r>0&&(this.bind(C,b),setTimeout(function(){S||b.call(j)},1e3*(r+v)+25)),this.size()&&this.get(0).clientLeft,this.css(E),0>=r&&setTimeout(function(){j.each(function(){b.call(this)})},0),this},v=null}(Zepto),function(t,e){function n(n,i,r,o,a){"function"!=typeof i||a||(a=i,i=e);var s={opacity:r};return o&&(s.scale=o,n.css(t.fx.cssPrefix+"transform-origin","0 0")),n.animate(s,i,null,a)}function i(e,i,r,o){return n(e,i,0,r,function(){a.call(t(this)),o&&o.call(this)})}var r=window.document,o=(r.documentElement,t.fn.show),a=t.fn.hide,s=t.fn.toggle;t.fn.show=function(t,i){return o.call(this),t===e?t=0:this.css("opacity",0),n(this,t,1,"1,1",i)},t.fn.hide=function(t,n){return t===e?a.call(this):i(this,t,"0,0",n)},t.fn.toggle=function(n,i){return n===e||"boolean"==typeof n?s.call(this,n):this.each(function(){var e=t(this);e["none"==e.css("display")?"show":"hide"](n,i)})},t.fn.fadeTo=function(t,e,i){return n(this,t,e,null,i)},t.fn.fadeIn=function(t,e){var n=this.css("opacity");return n>0?this.css("opacity",0):n=1,o.call(this).fadeTo(t,n,e)},t.fn.fadeOut=function(t,e){return i(this,t,null,e)},t.fn.fadeToggle=function(e,n){return this.each(function(){var i=t(this);i[0==i.css("opacity")||"none"==i.css("display")?"fadeIn":"fadeOut"](e,n)})}}(Zepto),function(t){function e(e,i){var u=e[s],c=u&&r[u];if(void 0===i)return c||n(e);if(c){if(i in c)return c[i];var l=a(i);if(l in c)return c[l]}return o.call(t(e),i)}function n(e,n,o){var u=e[s]||(e[s]=++t.uuid),c=r[u]||(r[u]=i(e));return void 0!==n&&(c[a(n)]=o),c}function i(e){var n={};return t.each(e.attributes||u,function(e,i){0==i.name.indexOf("data-")&&(n[a(i.name.replace("data-",""))]=t.zepto.deserializeValue(i.value))}),n}var r={},o=t.fn.data,a=t.camelCase,s=t.expando="Zepto"+ +new Date,u=[];t.fn.data=function(i,r){return void 0===r?t.isPlainObject(i)?this.each(function(e,r){t.each(i,function(t,e){n(r,t,e)})}):0 in this?e(this[0],i):void 0:this.each(function(){n(this,i,r)})},t.fn.removeData=function(e){return"string"==typeof e&&(e=e.split(/\s+/)),this.each(function(){var n=this[s],i=n&&r[n];i&&t.each(e||i,function(t){delete i[e?a(this):t]})})},["remove","empty"].forEach(function(e){var n=t.fn[e];t.fn[e]=function(){var t=this.find("*");return"remove"===e&&(t=t.add(this)),t.removeData(),n.call(this)}})}(Zepto),function(t){function e(e){return e=t(e),!(!e.width()&&!e.height())&&"none"!==e.css("display")}function n(t,e){t=t.replace(/=#\]/g,'="#"]');var n,i,r=s.exec(t);if(r&&r[2]in a&&(n=a[r[2]],i=r[3],t=r[1],i)){var o=Number(i);i=isNaN(o)?i.replace(/^["']|["']$/g,""):o}return e(t,n,i)}var i=t.zepto,r=i.qsa,o=i.matches,a=t.expr[":"]={visible:function(){return e(this)?this:void 0},hidden:function(){return e(this)?void 0:this},selected:function(){return this.selected?this:void 0},checked:function(){return this.checked?this:void 0},parent:function(){return this.parentNode},first:function(t){return 0===t?this:void 0},last:function(t,e){return t===e.length-1?this:void 0},eq:function(t,e,n){return t===n?this:void 0},contains:function(e,n,i){return t(this).text().indexOf(i)>-1?this:void 0},has:function(t,e,n){return i.qsa(this,n).length?this:void 0}},s=new RegExp("(.*):(\\w+)(?:\\(([^)]+)\\))?$\\s*"),u=/^\s*>/,c="Zepto"+ +new Date;i.qsa=function(e,o){return n(o,function(n,a,s){try{var l;!n&&a?n="*":u.test(n)&&(l=t(e).addClass(c),n="."+c+" "+n);var f=r(e,n)}catch(h){throw console.error("error performing selector: %o",o),h}finally{l&&l.removeClass(c)}return a?i.uniq(t.map(f,function(t,e){return a.call(t,e,f,s)})):f})},i.matches=function(t,e){return n(e,function(e,n,i){return!(e&&!o(t,e)||n&&n.call(t,null,i)!==t)})}}(Zepto),function(t){function e(t,e,n,i){return Math.abs(t-e)>=Math.abs(n-i)?t-e>0?"Left":"Right":n-i>0?"Up":"Down"}function n(){l=null,h.last&&(h.el.trigger("longTap"),h={})}function i(){l&&clearTimeout(l),l=null}function r(){s&&clearTimeout(s),u&&clearTimeout(u),c&&clearTimeout(c),l&&clearTimeout(l),s=u=c=l=null,h={}}function o(t){return("touch"==t.pointerType||t.pointerType==t.MSPOINTER_TYPE_TOUCH)&&t.isPrimary}function a(t,e){return t.type=="pointer"+e||t.type.toLowerCase()=="mspointer"+e}var s,u,c,l,f,h={},p=750;t(document).ready(function(){var d,m,v,g,y=0,x=0;"MSGesture"in window&&(f=new MSGesture,f.target=document.body),t(document).bind("MSGestureEnd",function(t){var e=t.velocityX>1?"Right":t.velocityX<-1?"Left":t.velocityY>1?"Down":t.velocityY<-1?"Up":null;e&&(h.el.trigger("swipe"),h.el.trigger("swipe"+e))}).on("touchstart MSPointerDown pointerdown",function(e){(!(g=a(e,"down"))||o(e))&&(v=g?e:e.touches[0],e.touches&&1===e.touches.length&&h.x2&&(h.x2=void 0,h.y2=void 0),d=Date.now(),m=d-(h.last||d),h.el=t("tagName"in v.target?v.target:v.target.parentNode),s&&clearTimeout(s),h.x1=v.pageX,h.y1=v.pageY,m>0&&250>=m&&(h.isDoubleTap=!0),h.last=d,l=setTimeout(n,p),f&&g&&f.addPointer(e.pointerId))
}).on("touchmove MSPointerMove pointermove",function(t){(!(g=a(t,"move"))||o(t))&&(v=g?t:t.touches[0],i(),h.x2=v.pageX,h.y2=v.pageY,y+=Math.abs(h.x1-h.x2),x+=Math.abs(h.y1-h.y2))}).on("touchend MSPointerUp pointerup",function(n){(!(g=a(n,"up"))||o(n))&&(i(),h.x2&&Math.abs(h.x1-h.x2)>30||h.y2&&Math.abs(h.y1-h.y2)>30?c=setTimeout(function(){h.el.trigger("swipe"),h.el.trigger("swipe"+e(h.x1,h.x2,h.y1,h.y2)),h={}},0):"last"in h&&(30>y&&30>x?u=setTimeout(function(){var e=t.Event("tap");e.cancelTouch=r,h.el.trigger(e),h.isDoubleTap?(h.el&&h.el.trigger("doubleTap"),h={}):s=setTimeout(function(){s=null,h.el&&h.el.trigger("singleTap"),h={}},250)},0):h={}),y=x=0)}).on("touchcancel MSPointerCancel pointercancel",r),t(window).on("scroll",r)}),["swipe","swipeLeft","swipeRight","swipeUp","swipeDown","doubleTap","tap","singleTap","longTap"].forEach(function(e){t.fn[e]=function(t){return this.on(e,t)}})}(Zepto),function(t){t.fn.end=function(){return this.prevObject||t()},t.fn.andSelf=function(){return this.add(this.prevObject||t())},"filter,add,not,eq,first,last,find,closest,parents,parent,children,siblings".split(",").forEach(function(e){var n=t.fn[e];t.fn[e]=function(){var t=n.apply(this,arguments);return t.prevObject=this,t}})}(Zepto);</script>
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/iscroll.js"></script>
<script>
	function resize() {
		window.remFontSize = document.documentElement.clientWidth / 10;
		document.documentElement.style.fontSize = document.documentElement.clientWidth / 10 + "px";
		$("body").append('<p id="remset" style="width:10rem;"></p>');
	    var realrem= $("#remset").width()/10;
	     var rem=document.documentElement.clientWidth/10;
	     if(realrem!=rem)
	     {
	      $("html").css('font-size',(rem*rem)/realrem+"px");
	     }
	     $("#remset").remove();
	}
	var b = null;
	window.addEventListener("resize", function() {
		clearTimeout(b),
			b = setTimeout(resize, 300)
	}, !1);
	resize();
	
</script>
<script type="text/javascript">

var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;


function pullUpAction () {
	setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
		var el, li, i;
		el = document.getElementById('waterfall');

		for (i=0; i<3; i++) {
			li = document.createElement('li');
			li.innerText = 'Generated row ' + (++generatedCount);
			el.appendChild(li, el.childNodes[0]);
		}
		
		myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
	}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
}

function loaded() {
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('waterfall', {
		useTransition: true,
		onRefresh: function () {
			if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多......';
			}
		},
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('waterfall').style.left = '0'; }, 800);
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
</script>

<script type="text/javascript">
	$().ready(function() {
	
		$(".dropdown").on("tap",function() {
			$(".icon-unfold").hasClass("jiantou") ? $(".icon-unfold").removeClass("jiantou").addClass("jiantou2") : $(".icon-unfold").removeClass("jiantou2").addClass("jiantou"),
			$(".downlist").hasClass("fromtop") ? ($(".downlist").removeClass("fromtop"), setTimeout(function() {
				$(".downlist").hide()
			},
			600)) : ($(".downlist").css("display", ""), setTimeout(function() {
				$(".downlist").addClass("fromtop")
			},
			100))
		})
	});
		
</script>

</head>
<body>

	<div class="reflesh">
		<div class="top">
				<ul>
					<li class="hanghuo">正品保障</li>
					<li class="baoyou">全国包邮</li>
					<li class="invoice">无忧售后</li>
				</ul>
		</div>
		
		<div id="waterfall" class="waterfall" style="min-height: 774px;">
			<div class="nav">
				<div class="navcontent"
					style="position: fixed; top: 0px; z-index: 42; transform: translateZ(0px);">
					<div class="slidewrap" id="nav">
						<div id="scroller"
							style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
							<ul>
								<li><a href="javascript:;" data-index="0" data-catid="81" isneedscar="false"
									class="current">安全座椅</a></li>
								<li><a href="javascript:;" data-index="1" data-catid="83" isneedscar="false">行车记录</a></li>
								<li><a href="javascript:;" data-index="2" data-catid="85" isneedscar="false">车载音箱</a></li>
								<li><a href="javascript:;" data-index="3" data-catid="87" isneedscar="false">公仔摆件</a></li>
								<li><a href="javascript:;" data-index="4" data-catid="89" isneedscar="false">空气净化器</a></li>
								<li><a href="javascript:;" data-index="5" data-catid="91" isneedscar="false">车载吸尘器</a></li>
								<li><a href="javascript:;" data-index="6" data-catid="93" isneedscar="false">头枕腰靠</a></li>
								<li><a href="javascript:;" data-index="7" data-catid="95" isneedscar="false">车载香水</a></li>
								<li><a href="javascript:;" data-index="8" data-catid="97" isneedscar="false">3M车蜡</a></li>
								<li><a href="javascript:;" data-index="9" data-catid="99" isneedscar="false">专车脚垫</a></li>
								<li><a href="javascript:;" data-index="10" data-catid="127" isneedscar="false">车载冰箱</a></li>
							</ul>
						</div>
					</div>
					<div class="dropdown">
						<div class="iconfont icon-unfold"></div>
					</div>
					<div class="downlist" style="display: none">
						<ul>
							<li><a href="javascript:;" data-index="0" data-catid="81" isneedscar="false"
								class="current">安全座椅</a></li>
							<li><a href="javascript:;" data-index="1" data-catid="83" isneedscar="false">行车记录</a></li>
							<li><a href="javascript:;" data-index="2" data-catid="85" isneedscar="false">车载音箱</a></li>
							<li><a href="javascript:;" data-index="3" data-catid="87" isneedscar="false">公仔摆件</a></li>
							<li><a href="javascript:;" data-index="4" data-catid="89" isneedscar="false">空气净化器</a></li>
							<li><a href="javascript:;" data-index="5" data-catid="91" isneedscar="false">车载吸尘器</a></li>
							<li><a href="javascript:;" data-index="6" data-catid="93" isneedscar="false">头枕腰靠</a></li>
							<li><a href="javascript:;" data-index="7" data-catid="95" isneedscar="false">车载香水</a></li>
							<li><a href="javascript:;" data-index="8" data-catid="97" isneedscar="false">3M车蜡</a></li>
							<li><a href="javascript:;" data-index="9" data-catid="99" isneedscar="false">专车脚垫</a></li>
							<li><a href="javascript:;" data-index="10" data-catid="127" isneedscar="false">车载冰箱</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="g-main" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
				
				<div class="g-mainc" id="8632">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/upload/shop/2015/12/018057eea116b0a0.jpg"
							src="http://static.qichechaoren.com/upload/shop/2015/12/018057eea116b0a0.jpg" id="8632"
							class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新款环球娃娃儿童安全座椅1029B-H 神奇宝贝玫瑰红</p>
						<p class="u-price">
							<span class="price">￥<em>799</em></span><span class="hassold">8人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50345">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/02/a5c5d2a9746d20af,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/02/a5c5d2a9746d20af,h_300,w_300.jpg"
							id="50345" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1031 探险家红黑</p>
						<p class="u-price">
							<span class="price">￥<em>829</em></span><span class="hassold">7人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50351">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/460ca613d2416519,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/460ca613d2416519,h_300,w_300.jpg"
							id="50351" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1028 月光宝贝红黑</p>
						<p class="u-price">
							<span class="price">￥<em>899</em></span><span class="hassold">6人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50347">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/02/06821ecd74dcd116,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/02/06821ecd74dcd116,h_300,w_300.jpg"
							id="50347" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1031 探险家斑马</p>
						<p class="u-price">
							<span class="price">￥<em>829</em></span><span class="hassold">5人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
			</div>
			<div class="dropload-down" id="pullUp"
				style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;">
				<div class="dropload-load" style="display: none;">
					<span class="pullUpLabel"></span>
				</div>
			</div>
			<div class="dropload-noData dropload-noData1">
				<span>已经最后一页了</span>
			</div>
		</div>
	</div>
	<div class="tips"></div>
	
</body>
</html>