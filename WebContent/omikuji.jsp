<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>おみくじ結果</title>
	</head>
	<body>
		<h2>結果</h2>
		<table>
			<tr>
			<td colspan = "2">今日の運勢は${omikuji.fortune.fortuneName}です。</td>
			</tr>
			<tr>
			<th>願い事：</th>
			<td>${omikuji.negaigoto}</td>
			</tr>
			<tr>
			<th>商い：</th>
			<td>${omikuji.akinai}</td>
			</tr>
			<tr>
			<th>学問：</th>
			<td>${omikuji.gakumon}</td>
			</tr>
		</table>
	</body>
</html>