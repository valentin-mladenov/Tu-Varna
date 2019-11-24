using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Pharmacy.WebApp.Models;
using Pharmacy.WebApp.Models.DataAccessLayer;

namespace Pharmacy.WebApp.Controllers
{
    public class FirmsController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: Firms
        public ActionResult Index()
        {
            return View(db.Firms.ToList());
        }

        // GET: Firms/Details/5
        public ActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Firm firm = db.Firms.Find(id);
            if (firm == null)
            {
                return HttpNotFound();
            }
            return View(firm);
        }

        // GET: Firms/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Firms/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Address,Name,Code,EIK")] Firm firm)
        {
            if (ModelState.IsValid)
            {
                firm.Id = Guid.NewGuid();
                db.Firms.Add(firm);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(firm);
        }

        // GET: Firms/Edit/5
        public ActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Firm firm = db.Firms.Find(id);
            if (firm == null)
            {
                return HttpNotFound();
            }
            return View(firm);
        }

        // POST: Firms/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Address,Name,Code,EIK")] Firm firm)
        {
            if (ModelState.IsValid)
            {
                db.Entry(firm).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(firm);
        }

        // GET: Firms/Delete/5
        public ActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Firm firm = db.Firms.Find(id);
            if (firm == null)
            {
                return HttpNotFound();
            }
            return View(firm);
        }

        // POST: Firms/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(Guid id)
        {
            Firm firm = db.Firms.Find(id);
            db.Firms.Remove(firm);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
