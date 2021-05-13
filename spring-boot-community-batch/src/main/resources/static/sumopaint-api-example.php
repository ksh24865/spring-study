<!DOCTYPE html>
<html lang="en">

	<head>
		<title>API Example</title>
	</head>
	
	<body>

		<h1>Sumo API in action</h1>
		
		<br>
		
		Click the image below to modify in Sumopaint:
		
		<br><br>
		
		<!-- OPEN WITH POST PARAMETER -->
		<form action="https://sumo.app/paint/en" method="GET">
			<input name="title" value="Image" />
			<input name="target" value="https://www.inni.com/api/paint.php" />
			<input name="url" value="https://www.inni.com/images/summer.jpg" />
		</form>
		
		<?php
		
			// check if data is set
			if(isset($_POST["imagedata"])) {
				// set id
				$id = uniqid();
				// file url where to save
				$file = "folder-you-want-to-save/".$id.".png";
				// file title (can be changed)
				$title = $_POST["title"]." Remix";
				// open file handle for saving
				$handle = fopen($file,"w");
				// decode and write to new image
				fwrite($handle, base64_decode(str_replace(' ','+',str_replace('data:image/png;base64,','',$_POST["imagedata"])));
				// close the handle
				fclose($handle);
				// show the modified image
				print "<br><br>Here is your modified image ($title):<br><br><img src='folder-you-want-to-save/".$id.".png'/><br>";
			}
			
		?>
				
	</body>

</html>
