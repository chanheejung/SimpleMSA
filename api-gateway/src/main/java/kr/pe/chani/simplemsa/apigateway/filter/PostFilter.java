package kr.pe.chani.simplemsa.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Component
public class PostFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
	    log.info("===== START Post Filter. =====");

		int httpStatus = ctx.getResponse().getStatus();
		String respHttpBody = getRespHttpBody(ctx);
		log.info("[Post Filter] HttpStatus : {}, Response HttpBody : {}", httpStatus, respHttpBody);

		return null;
	}


	private String getRespHttpBody(RequestContext ctx) {
		String responseBody = ctx.getResponseBody();
		if (responseBody == null) {
			InputStream is  = ctx.getResponseDataStream();
			try {
				byte[] ib = StreamUtils.copyToByteArray(is);
				responseBody = new String(ib, StandardCharsets.UTF_8);
				ctx.setResponseDataStream(new ByteArrayInputStream(ib));
			} catch (IOException e) {
				log.error("It is failed to obtainning Response Http Body.", e);
			}
		}
		return responseBody;
	}
}
