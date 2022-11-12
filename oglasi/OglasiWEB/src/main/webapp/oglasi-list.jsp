<c:forEach items="${oglasi}" var="oglas">
	<a href="/OglasiWEB/OglasPrijavaServlet?oglasID=${oglas.idOglas}" class="oglas-item">
		<font size="1">napisao <b style="display: inline">${oglas.oglasKorisnik.username}</b></font>
		<br>
		<font size="1">pregleda: ${oglas.brojPregleda}</font>
		<br>
		<span class="oglas-text">${oglas.text}</span>
	</a>
</c:forEach>