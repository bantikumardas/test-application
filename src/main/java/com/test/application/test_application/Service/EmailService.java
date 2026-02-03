package com.test.application.test_application.Service;

import com.test.application.test_application.DTO.TestInvitationDTO;
import com.test.application.test_application.Entity.Test;
import com.test.application.test_application.Entity.Users;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    public Boolean sendInvitation(String reciverEmail, String code,
//                                  TestInvitationDTO testInvitationDTO, Users sendUser, Test test){
//        try{
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setTo(reciverEmail);
//            helper.setFrom("bantikumardas43@gmail.com");
//            helper.setSubject("\uD83D\uDCBB Test Invitation from "+testInvitationDTO.getCompanyName());
//            String body= """
//                    <!-- START: Invitation Email HTML -->
//                    <!doctype html>
//                    <html lang="en">
//                    <head>
//                      <meta charset="utf-8" />
//                      <meta name="viewport" content="width=device-width,initial-scale=1" />
//                      <title>Test Invitation</title>
//                      <style>
//                        /* Fallback fonts and some basic resets for email clients */
//                        body,table,td,a { -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; }
//                        table,td { mso-table-lspace:0pt; mso-table-rspace:0pt; }
//                        img { -ms-interpolation-mode:bicubic; }
//                        img { border:0; height:auto; line-height:100%; outline:none; text-decoration:none; }
//                        body { margin:0; padding:0; width:100% !important; height:100% !important; background-color:#f2f4f6; font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; }
//                        .container { width:100%; max-width:680px; margin:0 auto; background:#ffffff; border-radius:8px; overflow:hidden; }
//                        .header { padding:20px 24px; display:flex; align-items:center; gap:12px; }
//                        .logo { width:48px; height:48px; border-radius:8px; object-fit:cover; }
//                        .brand { font-size:18px; font-weight:700; color:#0f1724; }
//                        .hero { padding:28px 24px; text-align:left; }
//                        .title { font-size:20px; margin:0 0 8px; color:#0f1724; }
//                        .lead { margin:0 0 16px; color:#415160; font-size:15px; line-height:1.45; }
//                        .card { background:#f8fafc; border:1px solid #e6eef6; padding:16px; border-radius:8px; display:flex; align-items:center; justify-content:space-between; gap:16px; }
//                        .code-box { font-family: 'Courier New', Courier, monospace; font-size:20px; font-weight:700; letter-spacing:2px; background:#ffffff; border:1px dashed #dceefb; padding:12px 16px; border-radius:8px; color:#0b5ed7; display:inline-block; }
//                        .meta { text-align:right; font-size:13px; color:#475569; }
//                        .details { margin-top:16px; display:flex; gap:12px; flex-wrap:wrap; }
//                        .detail-item { background:#fff; border:1px solid #eef3f8; padding:10px 12px; border-radius:6px; font-size:13px; color:#334155; min-width:140px; }
//                        .cta { margin-top:22px; text-align:left; }
//                        .btn { display:inline-block; padding:12px 20px; background:#0b5ed7; color:#fff; border-radius:8px; text-decoration:none; font-weight:600; }
//                        .note { margin-top:18px; color:#64748b; font-size:13px; }
//                        .footer { padding:18px 24px; font-size:13px; color:#94a3b8; text-align:center; background:#fbfdff; }
//                        .muted { color:#94a3b8; font-size:12px; }
//                        /* small screen */
//                        @media screen and (max-width:480px) {
//                          .header { padding:12px 16px; }
//                          .hero { padding:18px 16px; }
//                          .title { font-size:18px; }
//                          .code-box { font-size:18px; padding:10px 12px; }
//                        }
//                      </style>
//                    </head>
//                    <body>
//                      <center style="width:100%; background-color:#f2f4f6; padding:24px 0;">
//                        <table role="presentation" width="100%" align="center" cellpadding="0" cellspacing="0" style="max-width:680px; margin:0 auto;">
//                          <tr>
//                            <td>
//                              <div class="container" role="article" aria-roledescription="email">
//                                <!-- Header -->
//                                <div class="header">
//                                  <img src="{{LOGO_URL}}" alt="{{COMPANY_NAME}} Logo" class="logo" />
//                                  <div>
//                                    <div class="brand">{{COMPANY_NAME}}</div>
//                                    <div class="muted">Online Test Invitation</div>
//                                  </div>
//                                </div>
//
//                                <!-- Hero -->
//                                <div class="hero">
//                                  <h1 class="title">You're invited to take <strong>{{TEST_NAME}}</strong></h1>
//                                  <p class="lead">Hello, <strong>CANDIDATE_NAME</strong> — you've been invited to take a test by <strong>{{COMPANY_NAME}}</strong>. Please find your test details below and start the assessment before it expires.</p>
//
//                                  <div class="card" role="group" aria-label="test code and expiry">
//                                    <div>
//                                      <div class="muted">Test Code</div>
//                                      <div class="code-box" aria-label="test code">{{TEST_CODE}}</div>
//                                    </div>
//                                    <div class="meta">
//                                      <div class="muted">Expires on</div>
//                                      <div style="font-weight:700; color:#0f1724;">{{EXPIRY}}</div>
//                                    </div>
//                                  </div>
//
//                                  <div class="details">
//                                    <div class="detail-item">
//                                      <div class="muted">Duration</div>
//                                      <div style="font-weight:600;">{{DURATION}} mins</div>
//                                    </div>
//                                    <div class="detail-item">
//                                      <div class="muted">Total Questions</div>
//                                      <div style="font-weight:600;">{{TOTAL_QUESTIONS}}</div>
//                                    </div>
//                                    <div class="detail-item">
//                                      <div class="muted">Language</div>
//                                      <div style="font-weight:600;">{{LANGUAGE}}</div>
//                                    </div>
//                                  </div>
//
//                                  <div class="cta">
//                                    <a href="{{START_LINK}}" class="btn" target="_blank" rel="noopener">Start Test</a>
//                                  </div>
//
//                                  <p class="note">If the Start Test button doesn't work, copy and paste the following link into your browser:</p>
//                                  <p style="word-break:break-all;"><a href="{{START_LINK}}" target="_blank" rel="noopener" style="color:#0b5ed7;">{{START_LINK}}</a></p>
//
//                                  <p class="note">If you have any issues, contact us at <a href="mailto:{{SUPPORT_EMAIL}}">{{SUPPORT_EMAIL}}</a>.</p>
//                                </div>
//
//                                <div class="footer">
//                                  This invitation was sent to <strong>{{CANDIDATE_EMAIL}}</strong>. If you believe this was sent in error, please contact <a href="mailto:{{SUPPORT_EMAIL}}">{{SUPPORT_EMAIL}}</a>.
//                                </div>
//                              </div>
//                            </td>
//                          </tr>
//                        </table>
//
//                        <!-- Small muted notice -->
//                        <div style="max-width:680px; margin:14px auto; font-size:12px; color:#94a3b8;">
//                          <div>© {{YEAR}} {{COMPANY_NAME}}. All rights reserved.</div>
//                        </div>
//                      </center>
//                    </body>
//                    </html>
//                    <!-- END: Invitation Email HTML -->
//                    """;
//
//            body = body
//                    .replace("{{COMPANY_NAME}}", "Indihood")
//                    .replace("{{CANDIDATE_NAME}}", "Banti Kumar")
//                    .replace("{{TEST_NAME}}", "Java Developer Assessment")
//                    .replace("{{TEST_CODE}}", "945182673")
//                    .replace("{{EXPIRY}}", "22 Oct 2025, 11:59 PM")
//                    .replace("{{DURATION}}", "60")
//                    .replace("{{TOTAL_QUESTIONS}}", "40")
//                    .replace("{{LANGUAGE}}", "English")
//                    .replace("{{START_LINK}}", "https://testportal.com/start/945182673")
//                    .replace("{{SUPPORT_EMAIL}}", "support@indihood.com")
//                    .replace("{{LOGO_URL}}", "https://yourcdn.com/logo.png")
//                    .replace("{{YEAR}}", String.valueOf(java.time.Year.now()));
//
//            helper.setText(body, true); // Set to true for HTML
//
//            mailSender.send(message);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
}
