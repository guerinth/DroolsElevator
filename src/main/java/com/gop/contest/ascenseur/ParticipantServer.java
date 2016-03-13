package com.gop.contest.ascenseur;

import static java.lang.String.format;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParticipantServer extends AbstractHandler {

	private final Logger LOGGER = LoggerFactory
			.getLogger(ParticipantServer.class);
	private static final DroolsService DROOLS_SRV = DroolsService.getInstance();

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			int cabin;
			switch (target) {
			case "/nextCommand":
				String commandes = DROOLS_SRV.nextCommand();
				baseRequest.getResponse().getWriter().println(commandes);
				LOGGER.info("{} {}", target, commandes);
				break;
			case "/call":
				Integer atFloor = Integer.valueOf(baseRequest
						.getParameter("atFloor"));
				DirectionEnum to = DirectionEnum.valueOf(baseRequest
						.getParameter("to"));
				LOGGER.info(format("%s atFloor %d to %s", target, atFloor, to));
				DROOLS_SRV.call(atFloor, to);
				break;
			case "/go":
				Integer floorToGo = Integer.valueOf(baseRequest
						.getParameter("floorToGo"));
				cabin = Integer.valueOf(baseRequest.getParameter("cabin"));
				LOGGER.info(format("%s floorToGo %d cabin %d", target,
						floorToGo, cabin));
				DROOLS_SRV.go(floorToGo, cabin);
				break;
			case "/userHasEntered":
				cabin = Integer.valueOf(baseRequest.getParameter("cabin"));
				LOGGER.info("{} cabin {}", target, cabin);
				DROOLS_SRV.userHasEntered(cabin);
				break;
			case "/userHasExited":
				cabin = Integer.valueOf(baseRequest.getParameter("cabin"));
				LOGGER.info("{} cabin {}", target, cabin);
				DROOLS_SRV.userHasExited(cabin);
				break;
			case "/reset":
				String cause = baseRequest.getParameter("cause");
				int lowerFloor = Integer.valueOf(baseRequest
						.getParameter("lowerFloor"));
				int higherFloor = Integer.valueOf(baseRequest
						.getParameter("higherFloor"));
				int cabinSize = Integer.valueOf(baseRequest
						.getParameter("cabinSize"));
				int cabinCount = Integer.valueOf(baseRequest
						.getParameter("cabinCount"));
				LOGGER.info(format(
						"%s lowerFloor %d higherFloor %d cabinSize %d cabinCount %d Cause %s",
						target, lowerFloor, higherFloor, cabinSize, cabinCount,
						cause));
				DROOLS_SRV
						.reset(lowerFloor, higherFloor, cabinSize, cabinCount);
				break;
			default:
				LOGGER.warn(target);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		baseRequest.setHandled(true);
	}

	public static void main(String... args) throws Exception {

		Integer port = 80;
		if (args.length == 1) {
			port = readPort(args[0], port);
		}
		System.out.println(format(
				"Démarrage de l'application. Ecoute du port %d.", port));

		Server server = new Server(port);
		server.setHandler(new ParticipantServer());
		server.start();
		server.join();
	}

	private static Integer readPort(String arg, Integer defaultPort) {
		try {
			return Integer.parseInt(arg);
		} catch (NumberFormatException e) {
			return defaultPort;
		}
	}
}
