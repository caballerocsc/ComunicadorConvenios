<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://www.servicios.co/pagos/schemas">
   <soapenv:Header/>
   <soapenv:Body>
      <sch:PagoResource>
         <sch:referenciaFactura>
            <sch:referenciaFactura>${ref}</sch:referenciaFactura>
         </sch:referenciaFactura>
         <sch:totalPagar>${tot}</sch:totalPagar>
      </sch:PagoResource>
   </soapenv:Body>
</soapenv:Envelope>