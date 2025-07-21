package com.win.ticket.application.port.in;

import com.win.client.application.port.in.GetClientState;
import com.win.managerat.application.port.in.ManagerAtState;
import com.win.serverdown.application.port.in.GetServerDownState;
import com.win.subticket.application.port.in.SubticketState;
import com.win.ticket.domain.Ticket;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketStateMapper {

    public static GetTicketState toState(Ticket ticket) {
        ManagerAtState managerAperture = new ManagerAtState(
                ticket.getManagerAtAperture().getManagerId(),
                ticket.getManagerAtAperture().getFullname()
        );

        ManagerAtState managerClose = ticket.getManagerAtClose() != null
                ? new ManagerAtState(ticket.getManagerAtClose().getManagerId(), ticket.getManagerAtClose().getFullname())
                : null;

        Set<SubticketState> subticketStates = ticket.getSubTickets().stream().map(subticket -> {
            ManagerAtState managerApertureSub = new ManagerAtState(
                    subticket.getCreateManagerAt().getManagerId(),
                    subticket.getCreateManagerAt().getFullname()
            );

            ManagerAtState managerCloseSub = subticket.getCloseManagerAt() != null
                    ? new ManagerAtState(subticket.getCloseManagerAt().getManagerId(), subticket.getCloseManagerAt().getFullname())
                    : null;

            List<GetServerDownState> serverdowns = subticket.getServerDowns().stream().map(serverDown ->
                    GetServerDownState.builder()
                            .serverdownId(serverDown.getBreakDownId())
                            .subticketId(subticket.getSubticketId())
                            .client(GetClientState.builder()
                                    .clientId(serverDown.getClient().getClientId())
                                    .documentCi(serverDown.getClient().getDocumentCi())
                                    .descriptionDepartament(serverDown.getClient().getDescriptionDepartament())
                                    .descriptionDistrict(serverDown.getClient().getDescriptionDistrict())
                                    .statusAccount(serverDown.getClient().getStatus())
                                    .descriptionBox(serverDown.getClient().getDescriptionBox())
                                    .codeBox(serverDown.getClient().getCto())
                                    .contrata(serverDown.getClient().getContrata())
                                    .serialNumber(serverDown.getClient().getSerialNumber())
                                    .orderCode(serverDown.getClient().getOrderCode())
                                    .portGpon(serverDown.getClient().getPortGpon())
                                    .build())
                            .build()
            ).toList();

            return SubticketState.builder()
                    .subticketId(subticket.getSubticketId())
                    .subticketCode(subticket.getSubticketCode())
                    .card(subticket.getCard())
                    .port(subticket.getPort())
                    .badPraxis(subticket.getBadPraxis())
                    .solutions(subticket.getSolutions())
                    .dateStopLabores(subticket.getDateStopLabores())
                    .dateStartLabores(subticket.getDateStartLabores())
                    .responsable(subticket.getResponsable())
                    .dateReportPext(subticket.getDateReportPext())
                    .city(subticket.getCity())
                    .closeEventAt(subticket.getCloseEventAt())
                    .statusSubticket(subticket.getStatusSubticket())
                    .countClient(subticket.getCountClient())
                    .createEventAt(subticket.getCreateEventAt())
                    .causeProblem(subticket.getCauseProblem())
                    .createManagerAt(managerApertureSub)
                    .closeManagerAt(managerCloseSub)
                    .serverdowns(serverdowns)
                    .ctoAffected(subticket.getCtoAffected())
                    .build();
        }).collect(Collectors.toSet());

        return new GetTicketState(
                ticket.getTicketId(),
                ticket.getCodeTicket(),
                managerAperture,
                managerClose,
                ticket.getStatusTicket(),
                ticket.getType(),
                ticket.getReport(),
                ticket.getDiagnosis(),
                ticket.getCreateAtEvent(),
                ticket.getCloseAtEvent(),
                ticket.getUnavailability(),
                ticket.getNodeAffected(),
                ticket.getOltAffected(),
                ticket.getComment(),
                ticket.getAssignTo(),
                subticketStates
        );
    }

    public static List<GetTicketState> toStates(List<Ticket> tickets) {
        return tickets.stream().map(TicketStateMapper::toState).toList();
    }
}
